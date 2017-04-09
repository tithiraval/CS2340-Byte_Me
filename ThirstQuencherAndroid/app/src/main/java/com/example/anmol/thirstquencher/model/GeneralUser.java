package com.example.anmol.thirstquencher.model;

/**
 * The class for a general user
 * @author Anmol
 * @version 2/20/17
 */
public class GeneralUser extends User {

    public GeneralUser() {
        super();
    }

    /**
     * Creates a new general user
     * @param email The email address of the user
     * @param password The password of the user
     */
    public GeneralUser(String email, String password) {
        super(email, password);
        super.setAccountType(UserType.USER);
    }
}
