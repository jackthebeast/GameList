package it.jgrassi.gamelist.model;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by j.grassi on 28/11/2017.
 */

public class Player implements Serializable {
    public String name;
    public int balance;
    public String avatarLink;
    public Date lastLogindate;
}
