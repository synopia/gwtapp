package org.synopia.gwtapp.accounts.shared.domain;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author synopia
 */
public class Transfer implements Serializable {
    public enum Direction {
        INCOME, EXPENSE
    }
    public long id;
    public Direction direction;
    public Account fromAccount;
    public Account toAccount;
    public BigInteger amount;
}
