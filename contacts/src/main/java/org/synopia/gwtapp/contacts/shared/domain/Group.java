package org.synopia.gwtapp.contacts.shared.domain;

import java.io.Serializable;
import java.util.List;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class Group implements Serializable {

	public String name;
    public int memberSize;
    public List<Contact> members = Lists.newArrayList();


    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    @Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Group) {
			return Objects.equal(name, ((Group) obj).name);
		}
		return false;
	}

}
