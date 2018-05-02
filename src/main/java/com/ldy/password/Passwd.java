package com.ldy.password;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * This represents the contents of the passwd entry for a given user
 */

public class Passwd {
    private String name;
    private String gecos;

    /**
     * Gets the user name of the user whose password is to be changed.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the gecos information:
     * The gecos field, or GECOS field is an entry in the /etc/passwd file on Unix, and similar operating systems.
     * It is typically used to record general information about the account or its user(s) such as their real name and phone number.
     * We don't use this, because we can't read the gecos info from a user that hasn't cbeen created or is on another machine.
     *
     * @return
     */
    public String getGecos() {
        return gecos;
    }

    /**
     * Gets the user name of the user whose password is to be changed.
     *
     * @return
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gecos information:
     * The gecos field, or GECOS field is an entry in the /etc/passwd file on Unix, and similar operating systems.
     * It is typically used to record general information about the account or its user(s) such as their real name and phone number.
     * We don't use this, because we can't read the gecos info from a user that hasn't cbeen created or is on another machine.
     *
     * @return
     */
    public void setGecos(String gecos) {
        this.gecos = gecos;
    }

    /**
     * Constructor for Passwd class
     *
     * @param name  user name
     * @param gecos users gecos information
     */
    public Passwd(String name, String gecos) {
        this.name = name;
        this.gecos = gecos;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
