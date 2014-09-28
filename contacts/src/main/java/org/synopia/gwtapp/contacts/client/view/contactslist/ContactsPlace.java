package org.synopia.gwtapp.contacts.client.view.contactslist;

import com.google.gwt.core.client.GWT;

import fr.putnami.pwt.core.mvp.client.MvpPlace;
import fr.putnami.pwt.core.mvp.client.ViewProxy;

public class ContactsPlace extends MvpPlace {

	public static final ContactsPlace INSTANCE = new ContactsPlace();

	public ContactsPlace() {
		super((ViewProxy) GWT.create(ContactsView.class), null);
	}

	@Override
	public MvpPlace getPlace(String token) {
		return ContactsPlace.INSTANCE;
	}

}
