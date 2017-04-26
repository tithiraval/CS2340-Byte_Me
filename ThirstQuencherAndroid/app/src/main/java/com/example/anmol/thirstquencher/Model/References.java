package com.example.anmol.thirstquencher.Model;

import java.util.List;

/**
 * holds reference methods to link between objects and their functionality
 * @author Anmol
 * @version 4/25/17
 */

public class References {

    // Firebase Strings
    public static final String USER_TABLE = "USERS";
    public static final String SOURCE_REPORT_TABLE = "SOURCE_REPORTS";
    public static final String QUALITY_REPORT_TABLE = "QUALITY_REPORTS";

    // List View String
    public final static String REPORT_ID = "Report ID";
    public final static String DATE_SUBMITTED = "Date Submitted";


    // User and Report References
    private static User currentUser = null;
    private static SourceReport currentSourceReport = null;
    private static QualityReport currentQualityReport = null;

    /**
     * returns the current user
     * @return current user
     */
    public static User getCurrentUser() {
        return currentUser;
    }

    /**
     * updates the current user
     * @param user the new user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    /**
     * updates the current source report
     * @param report the new source report
     */
    public static void setCurrentSourceReport(SourceReport report) {
        References.currentSourceReport = report;
    }

    /**
     * updates the current quality report
     * @param report the new quality report
     */
    public static void setCurrentQualityReport(QualityReport report) {
        References.currentQualityReport = report;
    }

    /**
     * Returns the current source report
     * @return the current source report
     */
    public static SourceReport getCurrentSourceReport() {
        return currentSourceReport;
    }

    /**
     * Returns the current quality report
     * @return the current quality report
     */
    public static QualityReport getCurrentQualityReport() {
        return currentQualityReport;
    }

    /**
     * creates the user based on its type of user
     * @param email the email address of the new user
     * @param password the password of the user
     * @param typeOfUser the type of the user
     * @return the newly created user
     */
    public static User createUser(String email, String password, String typeOfUser) {
        if (typeOfUser.equals("Manager")) {
            return new Manager(email, password);
        } else if (typeOfUser.equals("Worker")) {
            return new Worker(email, password);
        } else if (typeOfUser.equals("Admin")) {
            return new Admin(email, password);
        } else if (typeOfUser.equals("User")){
            return new GeneralUser(email, password);
        }
        return null;
    }

    /**
     * Gets the type of the water source
     * @param waterType The type of the water source
     * @return
     */
    public static WaterType getWaterType(String waterType) {
        if (waterType.equals("Bottled")) {
            return WaterType.BOTTLED;
        } else if (waterType.equals("Well")) {
            return WaterType.WELL;
        } else if (waterType.equals("Stream")) {
            return WaterType.STREAM;
        } else if (waterType.equals("Lake")) {
            return WaterType.LAKE;
        } else if (waterType.equals("Spring")) {
            return WaterType.SPRING;
        } else {
            return WaterType.OTHER;
        }
    }

    /**
     * returns appropriate water condition enum for the string
     * @param waterCondition some water condition
     * @return water condition enum
     */
    public static WaterCondition getWaterCondition(String waterCondition) {
        if (waterCondition.equals("Waste")) {
            return WaterCondition.WASTE;
        } else if (waterCondition.equals("Treatable-Clear")) {
            return WaterCondition.TREATABLE_CLEAR;
        } else if (waterCondition.equals("Treatable-Muddy")) {
            return WaterCondition.TREATABLE_MUDDY;
        } else {
            return WaterCondition.PORTABLE;
        }
    }

    /**
     * return the overall condition enum appropriate for the string
     * @param condition string of the overall condition
     * @return overall condition enum
     */
    public static OverallCondition getCondition(String condition) {
        if (condition.equals("Safe")) {
            return OverallCondition.SAFE;
        } else if (condition.equals("Treatable")) {
            return OverallCondition.Treatable;
        } else {
            return OverallCondition.UNSAFE;
        }
    }


}
