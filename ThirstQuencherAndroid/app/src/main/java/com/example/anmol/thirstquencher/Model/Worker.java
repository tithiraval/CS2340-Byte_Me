package com.example.anmol.thirstquencher.Model;

import com.example.anmol.thirstquencher.Controller.GeneralUser;

/**
 * Created by Anmol on 2/20/17.
 */
public class Worker extends GeneralUser {

    public Worker(String username, String password) {
        super(username, password);
        super.setAccountType(UserType.WORKER);
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
