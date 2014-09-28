package org.synopia.gwtapp.contacts.shared.constant;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface ErrorConstants extends ConstantsWithLookup {

	@DefaultStringValue("Unknown error")
	String runtimeExceptionTitle();

	@DefaultStringValue("An unknown exception occured. Please contact technical services if this Error is blocking")
	String runtimeExceptionMessage();
}
