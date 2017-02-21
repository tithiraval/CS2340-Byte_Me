package com.example.anmol.thirstquencher;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Anmol on 2/20/17.
 */
public abstract class User {

    public static List<String> legalUserTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private final String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserType accountType;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.emailAddress = "";
        this.homeAddress = "";
        this.title = "";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    protected void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getHomeAddress() {
        return this.homeAddress;
    }

    public String getTitle() {
        return this.title;
    }

    public UserType getAccountType() {
        return this.accountType;
    }
}
