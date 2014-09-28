package org.synopia.gwtapp.contacts.client.view.addressbook;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import fr.putnami.pwt.core.editor.client.event.DirtyEvent;
import fr.putnami.pwt.core.editor.client.helper.MessageHelper;
import fr.putnami.pwt.core.model.client.model.Model;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.service.client.ServiceProxy;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import fr.putnami.pwt.core.widget.client.Anchor;
import fr.putnami.pwt.core.widget.client.Button;
import fr.putnami.pwt.core.widget.client.Form;
import fr.putnami.pwt.core.widget.client.GridRow;
import fr.putnami.pwt.core.widget.client.InputMultiSelect;
import fr.putnami.pwt.core.widget.client.InputText;
import fr.putnami.pwt.core.widget.client.OutputList;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;
import fr.putnami.pwt.core.widget.client.event.ButtonEvent;
import org.synopia.gwtapp.contacts.shared.constant.AddressConstants;
import org.synopia.gwtapp.contacts.shared.constant.ContactConstants;
import org.synopia.gwtapp.contacts.shared.constant.GenderConstants;
import org.synopia.gwtapp.contacts.shared.constant.SampleCommonConstants;
import org.synopia.gwtapp.contacts.shared.domain.Contact;
import org.synopia.gwtapp.contacts.shared.domain.Group;
import org.synopia.gwtapp.contacts.shared.service.ContactService;

import java.util.Collection;
import java.util.List;

public class AddressBookView extends Composite implements View<AddressBookPlace> {

	interface Binder extends UiBinderLocalized<Widget, AddressBookView> {

		UiBinderLocalized<Widget, AddressBookView> BINDER = GWT.create(Binder.class);
	}

	interface ContactRemote extends ServiceProxy<AddressBookView, ContactService>, ContactService {
	}

	interface Constants extends ConstantsWithLookup, SampleCommonConstants, ContactConstants, AddressConstants, GenderConstants {

	}

	public interface GroupModel extends Model<Group> {

		Model<Group> MODEL = GWT.create(GroupModel.class);
	}

	public interface ContactModel extends Model<Contact> {

		Model<Contact> MODEL = GWT.create(ContactModel.class);
	}

	private final ContactRemote contactService = GWT.create(ContactRemote.class);
	private final Constants constants = GWT.create(Constants.class);

	@UiField
	InputText searchBox;

	@UiField
	OutputList<Group> groupsList;
	@UiField
	Form<Group> groupItemTemplate;

	@UiField
	OutputList<Contact> contactsList;
	@UiField
	Form<Contact> contactDetails;
	@UiField
	Form<Contact> contactItemTemplate;
	@UiField
	InputMultiSelect<String> groupSelect;
	@UiField
	GridRow editNameRow;
	@UiField
	GridRow viewNameRow;

	@UiField
	Button<Contact> newContactButton;
	@UiField
	Button<Contact> editContactButton;
	@UiField
	Button<Contact> cancelContactButton;
	@UiField
	Button<Contact> saveContactButton;

	private Contact selected = null;
	private Group displayedGroup;
	private List<Contact> displayedList;

	public AddressBookView() {
		initWidget(((UiBinder<HTMLPanel, AddressBookView>) GWT.create(Binder.class)).createAndBindUi(this));
		contactService.bindService(this);

		MessageHelper messageHelper = new MessageHelper(constants);

		contactDetails.setMessageHelper(messageHelper);
		contactDetails.initialize(ContactModel.MODEL);

		contactsList.setMessageHelper(messageHelper);
		contactsList.initialize(ContactModel.MODEL);
		contactItemTemplate.setMessageHelper(messageHelper);
		contactItemTemplate.initialize(ContactModel.MODEL);

		groupsList.setMessageHelper(messageHelper);
		groupsList.initialize(GroupModel.MODEL);
		groupItemTemplate.setMessageHelper(messageHelper);
		groupItemTemplate.initialize(GroupModel.MODEL);
	}

	@Override
	public void present(AddressBookPlace place) {
		contactService.getGroups();
	}

	@UiHandler("newContactButton")
	void onNewContact(ButtonEvent event) {
		editContact(new Contact());
	}

	@UiHandler("editContactButton")
	void onEditContact(ButtonEvent event) {
		editContact(selected);
	}

	@UiHandler("saveContactButton")
	void onSaveContact(ButtonEvent event) {
		Contact contactToSave = contactDetails.flush();
		if (!contactDetails.hasError()) {
			contactService.savePerson(contactToSave);
		}
	}

	@UiHandler("cancelContactButton")
	void onCancelContact(ButtonEvent event) {
		viewContact(selected);
	}

	@UiHandler("contactDetails")
	void onSaveContact(DirtyEvent event) {
		saveContactButton.setDisabled(false);
	}

	@UiHandler("searchResetButton")
	void onResetSearch(ButtonEvent event) {
		searchBox.edit(null);
		displayList(displayedGroup.members);
	}

	@UiHandler("searchBox")
	void onSearchBox(KeyPressEvent event) {
		final InputText source = (InputText) event.getSource();
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				String query = source.flush();
				if (query == null || query.length() == 0) {
					displayList(displayedList);
				}
				else {
					final String queryToCompare = query.toLowerCase().trim();
					Iterable<Contact> filteredIteable = Iterables.filter(displayedList, new Predicate<Contact>() {

						@Override
						public boolean apply(Contact contact) {
							return contact.name != null && contact.name.toLowerCase().contains(queryToCompare);
						}
					});
					displayList(Lists.newArrayList(filteredIteable));
				}
			}
		});
	}

	@UiHandler("selectGroupAnchor")
	void onClickGroupAnchor(ClickEvent event) {
		Anchor<Group> anchor = (Anchor<Group>) event.getSource();
		displayGroup(anchor.getValue());
	}

	@UiHandler("openContactAnchor")
	void onClickContactAnchor(ClickEvent event) {
		Anchor<Contact> anchor = (Anchor<Contact>) event.getSource();
		selectContact(anchor.getValue());
	}

	@AsyncHandler
	void onGetGroups(List<Group> result) {
		Collection<String> groupsItems = Lists.newArrayList();
		for (int i = 1; i < result.size(); i++) {
			groupsItems.add(result.get(i).name);
		}
		groupSelect.setItems(groupsItems);
		groupsList.edit(result);
		displayGroup(result.get(0));
	}

	@AsyncHandler
	void onSavePerson(Contact result) {
		present(null);
	}

	private void displayGroup(Group group) {
		this.displayedGroup = group;
		displayList(group.members);
	}

	private void displayList(List<Contact> contact) {
		this.displayedList = contact;
		contactsList.edit(Lists.<Contact> newArrayList(contact));
		if (displayedList.size() > 0) {
			viewContact(displayedList.get(0));
		}
	}

	private void selectContact(Contact contact) {
		if (this.selected != contact) {
			viewContact(contact);
		}
	}

	private void viewContact(Contact collaborator) {
		selected = collaborator;
		contactDetails.setReadonly(true);
		contactDetails.edit(collaborator);

		editContactButton.setDisabled(false);

		cancelContactButton.setDisabled(true);
		saveContactButton.setDisabled(true);

		editNameRow.setVisible(false);
		viewNameRow.setVisible(true);
	}

	private void editContact(Contact collaborator) {
		contactDetails.setReadonly(false);
		contactDetails.edit(collaborator);

		editContactButton.setDisabled(true);

		cancelContactButton.setDisabled(false);
		//		saveContactButton.setDisabled(false);

		editNameRow.setVisible(true);
		viewNameRow.setVisible(false);
	}
}
