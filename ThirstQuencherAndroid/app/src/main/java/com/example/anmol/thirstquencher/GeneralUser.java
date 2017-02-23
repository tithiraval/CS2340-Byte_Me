package com.example.anmol.thirstquencher;

/**
 * The class for a general user
 * @author Byte_Me (Team 10)
 * @version 2/20/17
 */
public class GeneralUser extends User {

    /**
     * Creates a new general user
     * @param username The username of the user
     * @param password The password of the user
     */
    public GeneralUser(String username, String password) {
        super(username, password);
        super.setAccountType(UserType.USER);
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
