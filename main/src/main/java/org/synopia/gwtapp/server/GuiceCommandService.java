package org.synopia.gwtapp.server;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import fr.putnami.pwt.core.service.server.service.AbstractCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.Map;

/**
 * @author synopia
 */
public class GuiceCommandService extends AbstractCommandService {

    private final Logger logger = LoggerFactory.getLogger(GuiceCommandService.class);
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("GuiceCommandService started");
        Injector injector = Guice.createInjector(new RpcModule());
        Map<Key<?>, Binding<?>> allBindings = injector.getAllBindings();
        for (Map.Entry<Key<?>, Binding<?>> entry : allBindings.entrySet()) {
            Key<?> key = entry.getKey();
            Class<?> rawType = key.getTypeLiteral().getRawType();
            boolean isInterface     = rawType.isInterface();
            boolean isRemoteService = RemoteService.class.isAssignableFrom(rawType);
            if( isRemoteService && isInterface ) {
                Object instance = injector.getInstance(key);
                logger.info("Found remote service " + rawType);
                if (instance != null ) {
                    injectService(rawType, instance);
                }
            }
        }
    }

    public static void main(String[] args) throws ServletException {
        new GuiceCommandService().init(null);
    }
}
