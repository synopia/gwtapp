package org.synopia.gwtapp.user.server;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.synopia.gwtapp.user.shared.domain.Session;
import org.synopia.gwtapp.user.shared.domain.User;
import org.synopia.gwtapp.user.shared.service.UserService;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author synopia
 */

public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final AtomicLong USER_SEQUENCE = new AtomicLong();
    private static final AtomicLong SESSION_SEQUENCE = new AtomicLong();

    private Map<Long, User> users = Maps.newHashMap();
    private Map<Long, Session> sessions = Maps.newHashMap();

    public UserServiceImpl() {
        USER_SEQUENCE.incrementAndGet();
        SESSION_SEQUENCE.incrementAndGet();
        User admin = new User();
        admin.login = "admin";
        admin.password = "password";
        createUser(admin);
        logger.info("UserService started");
    }

    @Override
    public Session login(String login, String password) {
        logger.info("login("+login+", "+password+")");
        User user = findByLogin(login);
        Session session = Session.EMPTY;
        if( user!=null && user.password.equals(password)) {
            session = findSession(user.id);
            if( session==null ) {
                session = createSession();
                logger.info("new session started ("+session.id+")");
                session.userId = user.id;
            }
        }

        return session;
    }

    public Session findSession(long sessionId) {
        return sessions.get(sessionId);
    }

    public User findByLogin(String login) {
        for (User user : users.values()) {
            if( user.login.equalsIgnoreCase(login)) {
                return user;
            }
        }
        return null;
    }

    public User createUser(User user) {
        if( user.isNew() ) {
            user.id = USER_SEQUENCE.incrementAndGet();
        }
        users.put(user.id, user);
        return user;
    }

    public Session createSession() {
        Session session = new Session();
        session.id = SESSION_SEQUENCE.incrementAndGet();
        sessions.put(session.id, session);
        return session;
    }

}
