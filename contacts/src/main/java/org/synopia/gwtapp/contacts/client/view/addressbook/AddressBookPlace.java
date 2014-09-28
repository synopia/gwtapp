package org.synopia.gwtapp.contacts.client.view.addressbook;

import com.google.gwt.core.client.GWT;

import fr.putnami.pwt.core.mvp.client.MvpPlace;
import fr.putnami.pwt.core.mvp.client.ViewProxy;

public class AddressBookPlace extends MvpPlace {

	public static final AddressBookPlace INSTANCE = new AddressBookPlace();

	public AddressBookPlace() {
		super((ViewProxy) GWT.create(AddressBookView.class), null);
	}

	@Override
	public MvpPlace getPlace(String token) {
		return AddressBookPlace.INSTANCE;
	}

}
