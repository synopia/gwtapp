package org.synopia.gwtapp.contacts.shared.domain;

import java.io.Serializable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

public class Address implements Serializable {

	public String street;
	public String postCode;
	public String city;

	@Override
	public String toString() {
		ToStringHelper helper = Objects.toStringHelper(this);
		helper.add("postCode", this.postCode);
		helper.add("city", this.city);
		return helper.toString();
	}

}
