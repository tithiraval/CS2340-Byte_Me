package com.example.anmol.thirstquencher.model;

/**
 * The implementation of the manager user class
 * @author Anmol
 * @version 2/20/17
 */
public class Manager extends Worker {

    public Manager() {
        super();
    }

    /**
     * Creates a new manager user
     * @param email The email address of the manager
     * @param password The password of the manager
     */
    public Manager(String email, String password) {
        super(email, password);
        super.setAccountType(UserType.MANAGER);
    }
}
