package org.synopia.gwtapp.contacts.shared.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import org.synopia.gwtapp.contacts.shared.domain.Contact;
import org.synopia.gwtapp.contacts.shared.domain.Group;

public interface ContactService extends RemoteService{

	List<Group> getGroups();

	List<Contact> getPeople();

	Contact getPerson(String name);

	Contact savePerson(Contact person);
}
