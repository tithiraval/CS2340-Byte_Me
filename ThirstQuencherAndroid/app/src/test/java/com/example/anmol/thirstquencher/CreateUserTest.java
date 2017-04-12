package com.example.anmol.thirstquencher;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.Model.UserType;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Anmol Lal on 4/5/17.
 * Tests the createUser(String email, String password, String typeOfUser) method in References.java
 */

public class CreateUserTest {

    private final String email = "example@abc.com";
    private final String password = "123456";
    private User testUser;

    /**
     * Sets testUser to null;
     */
    @Before
    public void setUp() {
        testUser = null;
    }


    /**
     * Checks if a GeneralUser is created properly
     */
    @Test
    public void testCheckGeneralUser() {
        testUser = References.createUser(email, password, "User");
        assertNotNull("Returns null but should return a GeneralUser", testUser);
        assertEquals("Email does not match the input email", email, testUser.getEmailAddress());
        assertEquals("Password does not match the input password", password, testUser.getPassword());
        assertEquals("Home address is not an empty string", "", testUser.getHomeAddress());
        assertEquals("Username is not an empty string", "", testUser.getUsername());
        assertEquals("Title is not an empty string", "", testUser.getTitle());
        assertEquals("Account Type is not USER", UserType.USER, testUser.getAccountTypeVal());

    }

    /**
     * Checks if an Admin is created properly
     */
    @Test
    public void testCheckAdmin() {
        testUser = References.createUser(email, password, "Admin");
        assertNotNull("Returns null but should return an Admin", testUser);
        assertEquals("Email does not match the input email", email, testUser.getEmailAddress());
        assertEquals("Password does not match the input password", password, testUser.getPassword());
        assertEquals("Home address is not an empty string", "", testUser.getHomeAddress());
        assertEquals("Username is not an empty string", "", testUser.getUsername());
        assertEquals("Title is not an empty string", "", testUser.getTitle());
        assertEquals("Account Type is not Admin", UserType.ADMIN, testUser.getAccountTypeVal());

    }

    /**
     * Checks if a Worker is created properly
     */
    @Test
    public void testCheckWorker() {
        testUser = References.createUser(email, password, "Worker");
        assertNotNull("Returns null but should return a Worker", testUser);
        assertEquals("Email does not match the input email", email, testUser.getEmailAddress());
        assertEquals("Password does not match the input password", password, testUser.getPassword());
        assertEquals("Home address is not an empty string", "", testUser.getHomeAddress());
        assertEquals("Username is not an empty string", "", testUser.getUsername());
        assertEquals("Title is not an empty string", "", testUser.getTitle());
        assertEquals("Account Type is not WORKER", UserType.WORKER, testUser.getAccountTypeVal());

    }

    /**
     * Checks if a Manager is created properly
     */
    @Test
    public void testCheckManager() {
        testUser = References.createUser(email, password, "Manager");
        assertNotNull("Returns null but should return a Manager", testUser);
        assertEquals("Email does not match the input email", email, testUser.getEmailAddress());
        assertEquals("Password does not match the input password", password, testUser.getPassword());
        assertEquals("Home address is not an empty string", "", testUser.getHomeAddress());
        assertEquals("Username is not an empty string", "", testUser.getUsername());
        assertEquals("Title is not an empty string", "", testUser.getTitle());
        assertEquals("Account Type is not MANAGER", UserType.MANAGER, testUser.getAccountTypeVal());

    }

    /**
     * Checks if null is returned on an invalid input for typeOfUser
     */
    @Test
    public void testCheckInvalid() {
        testUser = References.createUser(email, password, "randomInput");
        assertNull("Does not return null when the typeOfUser input is invalid", testUser);
    }

}
