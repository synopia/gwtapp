package org.synopia.gwtapp.contacts.shared.constant;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface AddressConstants extends ConstantsWithLookup {

	@DefaultStringValue("Street")
	String street();

	@DefaultStringValue("City")
	String city();

	@DefaultStringValue("Postcode")
	String postcode();
}
