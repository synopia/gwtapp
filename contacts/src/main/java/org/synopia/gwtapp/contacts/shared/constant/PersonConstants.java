package org.synopia.gwtapp.contacts.shared.constant;

import com.google.gwt.i18n.client.ConstantsWithLookup;

public interface PersonConstants extends ConstantsWithLookup {

	@DefaultStringValue("Gender")
	String gender();

	@DefaultStringValue("Name")
	String name();

	@DefaultStringValue("Enable")
	String enable();

	@DefaultStringValue("Login account enable")
	String enableCheckbox();

	@DefaultStringValue("Enter the full name of the person")
	String nameHelp();

	@DefaultStringValue("Name...")
	String namePlaceholder();

	@DefaultStringValue("Weight")
	String weight();

	@DefaultStringValue("Weight...")
	String weightPlaceholder();

	@DefaultStringValue("Birthday")
	String birthday();

	@DefaultStringValue("Day/Month/Year")
	String birthdayPlaceholder();

	@DefaultStringValue("Address")
	String address();

	@DefaultStringValue("Your not fat...")
	String weightTooltip();

	@DefaultStringValue("Boss")
	String bossNameHeader();

	@DefaultStringValue("Remarks")
	String remarks();

}
