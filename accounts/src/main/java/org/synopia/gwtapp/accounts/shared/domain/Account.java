package org.synopia.gwtapp.accounts.shared.domain;

import org.synopia.gwtapp.contacts.shared.domain.Contact;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author synopia
 */
public class Account implements Serializable{
    public long id;
    public Contact owner;
    public BigInteger soll;
    public BigInteger ist;
    public BigInteger fest;
}
