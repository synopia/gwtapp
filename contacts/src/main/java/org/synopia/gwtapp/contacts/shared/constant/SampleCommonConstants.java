package org.synopia.gwtapp.contacts.shared.constant;

import com.google.gwt.i18n.client.ConstantsWithLookup;

import fr.putnami.pwt.core.editor.shared.constant.ValidationConstants;

public interface SampleCommonConstants extends ConstantsWithLookup, ValidationConstants {

	@DefaultStringValue("Save")
	String saveButton();

	@DefaultStringValue("Cancel")
	String cancelButton();

	@DefaultStringValue("Submit")
	String submitButton();

	@DefaultStringValue("Close")
	String closeButton();

	@DefaultStringValue("Open")
	String openButton();

	@DefaultStringValue("Add")
	String addButton();
}
