package org.synopia.gwtapp.client.application;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.logging.client.ConsoleLogHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.error.client.widget.SimpleErrorDisplayer;
import fr.putnami.pwt.core.mvp.client.MvpController;
import fr.putnami.pwt.core.theme.client.CssLink;
import fr.putnami.pwt.core.theme.client.Theme;
import fr.putnami.pwt.core.theme.client.ThemeController;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;
import org.synopia.gwtapp.contacts.client.view.addressbook.AddressBookPlace;
import org.synopia.gwtapp.contacts.client.view.contactslist.ContactsPlace;
import org.synopia.gwtapp.contacts.shared.constant.ErrorConstants;
import org.synopia.gwtapp.transfer.client.view.TransferPlace;
import org.synopia.gwtapp.transfer.client.view.TransferPresenter;
import org.synopia.gwtapp.user.client.view.LoginComposite;

import java.util.logging.Logger;

public class MainPanel extends Composite implements AcceptsOneWidget {
    Logger logger = Logger.getLogger("MainPanel");
	interface Binder extends UiBinderLocalized<HTMLPanel, MainPanel> {
		Binder BINDER = GWT.create(Binder.class);
	}

	@UiField
	SimplePanel viewContainer;

    @UiField
    SimplePanel login;

//    @Inject
//    TransferPresenter presenter;

    @Inject
	public MainPanel(MvpController mvpController, ErrorManager errorManager, ThemeController themeController, TransferPlace transferPlace,  AddressBookPlace addressBookPlace, ContactsPlace contactsPlace, LoginComposite loginComposite) {
        logger.info("MainPanel ");
		initWidget(Binder.BINDER.createAndBindUi(this));

        Theme theme = new Theme();
        theme.addLink(new CssLink("theme/sample/style/pwt-sample-web.css", 0));
        themeController.installTheme(theme);

        mvpController.setDisplay(this);

        SimpleErrorDisplayer errorDisplayer = new SimpleErrorDisplayer();
        errorDisplayer.setConstants((ConstantsWithLookup) GWT.create(ErrorConstants.class));
        errorManager.setErrorDisplayer(errorDisplayer);

        mvpController.setDefaultPlace(transferPlace);

        mvpController.registerActivity(contactsPlace);
        mvpController.registerActivity(addressBookPlace);
        mvpController.registerActivity(transferPlace);

        login.setWidget(loginComposite);

        mvpController.handleCurrentHistory();
    }

    @Override
	public void setWidget(IsWidget w) {
		if (w == null) {
			return;
		}
		viewContainer.setWidget(w);
	}

}
