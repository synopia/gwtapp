package org.synopia.gwtapp.server;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import org.synopia.gwtapp.contacts.server.service.ContactServiceImpl;
import org.synopia.gwtapp.contacts.shared.service.ContactService;
import org.synopia.gwtapp.user.server.UserServiceImpl;
import org.synopia.gwtapp.user.shared.service.UserService;

/**
 * @author synopia
 */
public class RpcModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
        bind(ContactService.class).to(ContactServiceImpl.class).in(Singleton.class);
    }
}
