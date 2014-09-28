package org.synopia.gwtapp.user.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import org.synopia.gwtapp.user.shared.domain.Session;

/**
 * @author synopia
 */
public interface UserService extends RemoteService {
    Session login(String login, String password);
}
