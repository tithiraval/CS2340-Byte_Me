package com.example.anmol.thirstquencher;

/**
 * The implementation of the manager user class
 * @author Anmol
 * @version 2/20/17
 */
public class Manager extends Worker {

    /**
     * Creates a new manager user
     * @param username The username of the manager
     * @param password The password of the manager
     */
    public Manager(String username, String password) {
        super(username, password);
        super.setAccountType(UserType.MANAGER);
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
