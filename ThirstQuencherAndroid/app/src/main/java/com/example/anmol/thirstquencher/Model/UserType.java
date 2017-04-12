package com.example.anmol.thirstquencher.Model;

/**
 * The type that a user can be
 * @author Anmol
 * @version 2/20/17
 */
public enum UserType {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");
    private final String display;

    private UserType(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return this.display;
    }
}
