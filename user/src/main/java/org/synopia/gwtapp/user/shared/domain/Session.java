package org.synopia.gwtapp.user.shared.domain;

import java.io.Serializable;

/**
 * @author synopia
 */
public class Session implements Serializable {
    public static final Session EMPTY = new Session();
    public long userId;
    public long id;
}
