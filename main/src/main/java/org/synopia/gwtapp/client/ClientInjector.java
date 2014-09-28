package org.synopia.gwtapp.client;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import org.synopia.gwtapp.client.application.MainPanel;

/**
 * @author synopia
 */
@GinModules(ClientModule.class)
public interface ClientInjector extends Ginjector {
    MainPanel getMainPanel();
}
