package com.example.anmol.thirstquencher;

/**
 * Created by Anmol on 2/20/17.
 */
public class GeneralUser extends User {

    public GeneralUser(String username, String password) {
        super(username, password);
        super.setAccountType(UserType.USER);
    }

    protected void setAccountType(UserType accountType) {
        super.setAccountType(accountType);
    }

    public UserType getAccountType() {
        return super.getAccountType();
    }
}
