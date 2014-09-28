package org.synopia.gwtapp.contacts.shared.domain;

import java.util.List;

import com.google.common.collect.Lists;

public class Contact extends Person {

	public List<String> emails = Lists.newArrayList();
    public List<String> phones = Lists.newArrayList();
    public List<String> groups = Lists.newArrayList();
	public String remarks;

}
