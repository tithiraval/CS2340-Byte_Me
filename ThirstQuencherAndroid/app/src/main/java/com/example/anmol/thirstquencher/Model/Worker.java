package com.example.anmol.thirstquencher.Model;

import com.google.firebase.database.Exclude;

/**
 * Worker class
 * @author Anmol
 * @version 2/20/17.
 */
public class Worker extends GeneralUser {

    /**
     * empty constructor for worker
     */
    public Worker() {
        super();
    }

    /**
     * creates the new worker
     * @param email the email address of the worker
     * @param password the password of the worker
     */
    public Worker(String email, String password) {
        super(email, password);
        super.setAccountType(UserType.WORKER);
    }
}
