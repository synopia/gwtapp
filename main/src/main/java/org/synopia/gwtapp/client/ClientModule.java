package org.synopia.gwtapp.client;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import fr.putnami.pwt.core.error.client.ErrorManager;
import fr.putnami.pwt.core.mvp.client.MvpController;
import fr.putnami.pwt.core.theme.client.ThemeController;
import org.synopia.gwtapp.client.application.MainPanel;
import org.synopia.gwtapp.user.client.view.LoginComposite;


/**
 * @author synopia
 */
public class ClientModule extends AbstractGinModule {
    public static class ThemeControllerProvider implements Provider<ThemeController> {

        @Override
        public ThemeController get() {
            return ThemeController.get();
        }
    }
    public static class ErrorManagerProvider implements Provider<ErrorManager> {
        @Override
        public ErrorManager get() {
            return ErrorManager.get();
        }
    }
    public static class MvpControllerProvider implements Provider<MvpController> {
        @Override
        public MvpController get() {
            return MvpController.get();
        }
    }
    @Override
    protected void configure() {
        bind(ThemeController.class).toProvider(ThemeControllerProvider.class);
        bind(ErrorManager.class).toProvider(ErrorManagerProvider.class);
        bind(MvpController.class).toProvider(MvpControllerProvider.class);
//        bind(MainPanel.class).in(Singleton.class);
//        bind(LoginComposite.class).in(Singleton.class);
    }
}
