package com.example.anmol.thirstquencher;

import java.util.Arrays;
import java.util.List;

/**
 * Implementation of the user class
 * @author Byte_Me (Team 10)
 * @version 2/20/17
 */
public abstract class User {

    public static List<String> legalUserTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private final String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserType accountType;

    /**
     * Creates a new user
     * @param username The user name of the user
     * @param password The password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.emailAddress = "";
        this.homeAddress = "";
        this.title = "";
    }

    /**
     * Sets the password of the user
     * @param password What the password that should be set to
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the email address of the user
     * @param emailAddress What the email address should be set to
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Sets the home address of the user
     * @param homeAddress What the home address should be set to
     */
    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    /**
     * Sets the title of the user
     * @param title What the title should be set to
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the account type of the user
     * @param accountType What the account type should be set to
     */
    protected void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }

    /**
     * Gets the user name of the user
     * @return The user name of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the password of the user
     * @return The password of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets the email address of the user
     * @return The email address of the user
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Gets the home address of the user
     * @return The home address of the user
     */
    public String getHomeAddress() {
        return this.homeAddress;
    }

    /**
     * Gets the title of the user
     * @return The title of the user
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the account type of the user
     * @return The user's account type
     */
    public UserType getAccountType() {
        return this.accountType;
    }
}
