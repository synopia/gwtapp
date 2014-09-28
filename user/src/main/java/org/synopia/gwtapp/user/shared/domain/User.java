package org.synopia.gwtapp.user.shared.domain;

import java.io.Serializable;

/**
 * @author synopia
 */
public class User implements Serializable {
    public long id;
    public String login;
    public String password;


    public boolean isNew() {
        return id==0;
    }
}
