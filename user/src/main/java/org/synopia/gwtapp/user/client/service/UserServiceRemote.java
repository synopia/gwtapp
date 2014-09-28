package org.synopia.gwtapp.user.client.service;

import fr.putnami.pwt.core.service.client.ServiceProxy;
import org.synopia.gwtapp.user.client.view.LoginComposite;
import org.synopia.gwtapp.user.shared.service.UserService;

/**
 * @author synopia
 */
public interface UserServiceRemote extends ServiceProxy<LoginComposite, UserService>, UserService {
}
