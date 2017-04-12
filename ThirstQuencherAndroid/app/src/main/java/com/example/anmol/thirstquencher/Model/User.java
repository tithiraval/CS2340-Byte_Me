package com.example.anmol.thirstquencher.Model;

import com.google.firebase.database.Exclude;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the user class
 * @author Anmol
 * @version 2/20/17
 */
public abstract class User {

    public static List<String> legalUserTypes = Arrays.asList("User", "Worker", "Manager", "Admin");
    private String username;
    private String password;
    private String emailAddress;
    private String homeAddress;
    private String title;
    private UserType accountType;

    public User() {

    }

    /**
     * Creates a new user
     * @param email The email address of the user
     * @param password The password of the user
     */
    public User(String email, String password) {
        this.emailAddress = email;
        this.password = password;
        this.username = "";
        this.homeAddress = "";
        this.title = "";
    }

    /**
     * Sets the username of the user
     * @param username What the password that should be set to
     */
    public void setUsername(String username) {
        this.username = username;
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
     * @param accountType value of the account type
     */
    @Exclude
    public void setAccountType(UserType accountType) {
        this.accountType = accountType;
    }

    /**
     * Sets the account type of the user
     * @param accountType string value of the account type
     */
    public void setAccountType(String accountType) {
        this.accountType = UserType.valueOf(accountType);
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
     * @return The value representation of the user's account type
     */
    @Exclude
    public UserType getAccountTypeVal() {
        return this.accountType;
    }

    /**
     * Gets the account type of the user
     * @return The raw string representation of the user's account type
     */
    public String getAccountType() {
        return this.accountType.name();
    }

    @Exclude
    public Map<String, Object> updateProfile(String username, String homeAddress, String title) {
        Map<String, Object> result = new HashMap<String, Object>();

        result.put("username", username);
        result.put("homeAddress", homeAddress);
        result.put("title", title);

        return result;
    }
}
