package com.example.anmol.thirstquencher;

/**
 * The admin user class
 * @author Byte_Me (Team 10)
 * @version 2/20/17
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

    @Override
    protected void setAccountType(UserType accountType) {
        super.setAccountType(accountType);
    }

    @Override
    public UserType getAccountType() {
        return super.getAccountType();
    }
}
