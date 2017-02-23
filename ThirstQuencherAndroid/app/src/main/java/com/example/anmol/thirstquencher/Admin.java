package com.example.anmol.thirstquencher;

/**
 * The admin user class
 * @author Byte_Me (Team 10)
 * @version 1.0
 */
public class Admin extends User {

    /**
     * Creates the Admin user
     * @param username the username of the admin user
     * @param password the password of the admin user
     */
    public Admin(String username, String password) {
        super(username, password);
        super.setAccountType(UserType.ADMIN);
    }

    /**
     * Sets the account type of the user
     * @param accountType the account type to set the user to
     */
    protected void setAccountType(UserType accountType) {
        super.setAccountType(accountType);
    }

    /**
     * Gets the type of the user
     * @return the account type
     */
    public UserType getAccountType() {
        return super.getAccountType();
    }
}
