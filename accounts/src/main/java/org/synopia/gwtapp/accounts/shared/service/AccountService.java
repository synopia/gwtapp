package org.synopia.gwtapp.accounts.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import org.synopia.gwtapp.accounts.shared.domain.Account;
import org.synopia.gwtapp.accounts.shared.domain.Transfer;
import org.synopia.gwtapp.contacts.shared.domain.Contact;

/**
 * @author synopia
 */
public interface AccountService extends RemoteService {
    Account createAccount( Contact owner );
    Transfer createTransfer( Transfer transfer );
}
