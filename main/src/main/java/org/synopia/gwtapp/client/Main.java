package org.synopia.gwtapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import org.synopia.gwtapp.client.application.MainPanel;

import java.util.logging.Logger;

public class Main implements EntryPoint {
    Logger logger = Logger.getLogger("Main");
    private final ClientInjector injector = GWT.create(ClientInjector.class);

	@Override
	public void onModuleLoad() {
        logger.info("Main loaded");
        MainPanel applicationMenu = injector.getMainPanel();
		RootPanel.get().add(applicationMenu);
	}

}
