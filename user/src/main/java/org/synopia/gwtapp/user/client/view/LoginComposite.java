package org.synopia.gwtapp.user.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.inject.Inject;
import fr.putnami.pwt.core.service.client.annotation.AsyncHandler;
import fr.putnami.pwt.core.widget.client.binder.UiBinderLocalized;
import org.synopia.gwtapp.user.client.service.UserServiceRemote;
import org.synopia.gwtapp.user.shared.domain.Session;

/**
 * @author synopia
 */
public class LoginComposite extends Composite {
    interface Binder extends UiBinderLocalized<HTMLPanel, LoginComposite> {
        Binder BINDER = GWT.create(Binder.class);
    }

    @UiField
    HasText login;

    @UiField
    HasText password;

    @UiField
    Button submit;

    UserServiceRemote service;

    @Inject
    public LoginComposite(UserServiceRemote service) {
        initWidget(Binder.BINDER.createAndBindUi(this));
        this.service = service;
        service.bindService(this);
    }

    @UiHandler("submit")
    public void onSubmit( ClickEvent event ) {
        if( "Login".equals(login.getText())) {
            service.login(login.getText(), password.getText());
            submit.setText("...");
        } else {
            service.login("", "");
        }
    }

    @AsyncHandler(method = "login")
    public void onLoginResponse( Session session ) {
        if( session.id!=0 ) {
            submit.setText("Logout");
        } else {
            submit.setText("Login");
        }
    }
}
