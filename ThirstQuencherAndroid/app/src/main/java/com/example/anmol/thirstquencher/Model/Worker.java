package com.example.anmol.thirstquencher.Model;

import com.google.firebase.database.Exclude;

/**
 * Created by Anmol on 2/20/17.
 */
public class Worker extends GeneralUser {

    public Worker() {
        super();
    }

    public Worker(String email, String password) {
        super(email, password);
        super.setAccountType(UserType.WORKER);
    }
}
