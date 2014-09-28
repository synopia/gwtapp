package org.synopia.gwtapp.contacts.client.view.contactslist;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import fr.putnami.pwt.core.editor.client.helper.MessageHelper;
import fr.putnami.pwt.core.model.client.model.Model;
import fr.putnami.pwt.core.mvp.client.View;
import fr.putnami.pwt.core.service.client.ServiceProxy;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import fr.putnami.pwt.core.widget.client.Form;
import fr.putnami.pwt.core.widget.client.Modal;
import fr.putnami.pwt.core.widget.client.TableEditor;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;
import fr.putnami.pwt.core.widget.client.event.ButtonEvent;
import fr.putnami.pwt.core.widget.client.event.RowClickEvent;
import fr.putnami.pwt.core.widget.client.event.SelectionEvent;
import org.synopia.gwtapp.contacts.shared.constant.AddressConstants;
import org.synopia.gwtapp.contacts.shared.constant.GenderConstants;
import org.synopia.gwtapp.contacts.shared.constant.PersonConstants;
import org.synopia.gwtapp.contacts.shared.constant.SampleCommonConstants;
import org.synopia.gwtapp.contacts.shared.domain.Contact;
import org.synopia.gwtapp.contacts.shared.domain.Person;
import org.synopia.gwtapp.contacts.shared.service.ContactService;

public class ContactsView extends Composite implements View<ContactsPlace> {

	interface Binder extends UiBinderLocalized<HTMLPanel, ContactsView> {

		UiBinderLocalized<HTMLPanel, ContactsView> BINDER = GWT.create(Binder.class);
	}

	interface ContactRemote extends ServiceProxy<ContactsView, ContactService>, ContactService {
		ContactRemote SERVICE = (ContactRemote) GWT.create(ContactRemote.class);

	}

	interface Constants extends ConstantsWithLookup, SampleCommonConstants, PersonConstants, AddressConstants, GenderConstants {

		@DefaultStringValue("Edit a contact !")
		String newPersonTitle();

	}

	public interface ContactModel extends Model<Contact> {

		Model<Contact> MODEL = GWT.create(ContactModel.class);
	}

	@UiField(provided = true)
	final Constants constants = GWT.create(Constants.class);

	@UiField(provided = true)
	final List<Integer> weightItems = generateWeightItems();

	@UiField
	Form<Contact> contactEditor;

	@UiField
	TableEditor<Contact> contactTable;

	@UiField
	Modal modal;

	public ContactsView() {
		initWidget(((UiBinder<HTMLPanel, ContactsView>) GWT.create(Binder.class)).createAndBindUi(this));
		ContactRemote.SERVICE.bindService(this);

		MessageHelper messageHelper = new MessageHelper(constants);

		contactTable.setMessageHelper(messageHelper);
		contactTable.initialize(ContactModel.MODEL);
		contactEditor.setMessageHelper(messageHelper);
		contactEditor.initialize(ContactModel.MODEL);
	}

	@Override
	public void present(ContactsPlace place) {
		ContactRemote.SERVICE.getPeople();

	}

	@UiHandler("clickMeBoutton")
	void onClickMeBouttonEvent(ButtonEvent event) {
		contactEditor.edit(new Contact());
		modal.toggleVisibility();
	}

	@UiHandler("contactTable")
	void onRowClik(RowClickEvent event) {
		GWT.log("" + event.<Person> getValue().name);
	}

	@UiHandler("contactTableSelecter")
	void onPersonSelected(SelectionEvent event) {
		GWT.log("yop");
	}

	@UiHandler("selectContactBoutton")
	void onSelectContactEvent(ButtonEvent event) {
		Contact collab = event.getValue();
		contactEditor.edit(collab);
		modal.toggleVisibility();
	}

	@UiHandler("cancelButton")
	void onCancelButton(ButtonEvent event) {
		modal.hide();
	}

	@UiHandler("saveButton")
	void onSave(ButtonEvent event) {
		Contact flushed = contactEditor.flush();
		if (!contactEditor.hasError()) {
			ContactRemote.SERVICE.savePerson(flushed);
			modal.hide();
		}
	}

	@AsyncHandler
	void onGetPeople(List<Contact> result) {
		contactTable.edit(Lists.<Contact> newArrayList(result));
	}

	@AsyncHandler
	void onSavePerson(Contact result) {
		present(null);
	}

	private List<Integer> generateWeightItems() {
		List<Integer> result = Lists.newArrayList();
		for (int i = 30; i < 121; i++) {
			result.add(i);
		}
		return result;
	}

}
