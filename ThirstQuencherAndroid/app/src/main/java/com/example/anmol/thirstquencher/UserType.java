package com.example.anmol.thirstquencher;

/**
 * Created by Anmol on 2/20/17.
 */
public enum UserType {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");
    private final String display;

    private UserType(String display) {
        this.display = display;
    }

    public String toString() {
        return this.display;
    }
}
