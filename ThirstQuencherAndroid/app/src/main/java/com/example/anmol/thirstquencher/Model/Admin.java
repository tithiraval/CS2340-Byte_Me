package com.example.anmol.thirstquencher.Model;

import com.google.firebase.database.Exclude;

/**
 * The admin user class
 * @author Anmol
 * @version 2/20/17
 */
public class Admin extends User {

    public Admin() {
        super();
    }

    /**
     * Creates the Admin user
     * @param email the email address of the admin user
     * @param password the password of the admin user
     */
    public Admin(String email, String password) {
        super(email, password);
        super.setAccountType(UserType.ADMIN);
    }
}
