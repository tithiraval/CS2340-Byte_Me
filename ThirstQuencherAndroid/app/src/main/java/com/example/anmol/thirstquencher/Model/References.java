package com.example.anmol.thirstquencher.Model;

import java.util.List;

/**
 * holds reference methods to link between objects and their functionality
 * @author Anmol
 * @version 3/28/17
 */

public class References {

    // Firebase Strings
    public static final String USER_TABLE = "USERS";
    public static final String SOURCE_REPORT_TABLE = "SOURCE_REPORTS";
    public static final String QUALITY_REPORT_TABLE = "QUALITY_REPORTS";


    public final static String REPORT_ID = "Report ID";
    public final static String DATE_SUBMITTED = "Date Submitted";


    // Other References User
    private static User currentUser = null;
    private static List<SourceReport> waterReports;
    private static List<QualityReport> qualityReports;

    public static int numSourceReports;
    public static int numQualityReports;

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
     * returns the list of all the water reports
     * @return water reports list
     */
    public static List<SourceReport> getWaterReports() {
        return waterReports;
    }

    /**
     * updates the water report list
     * @param waterReports the water reports list
     */
    public static void setWaterReports(List<SourceReport> waterReports) {
        References.waterReports = waterReports;
    }

    /**
     * adds a new wate report to the water reports list
     * @param waterReport the water report we want to add
     */
    public static void addWaterReport(SourceReport waterReport) {
        waterReports.add(numSourceReports++, waterReport);
    }

    /**
     * returns a given source report based on its index in the list
     * @param index the index of the desired source report
     * @return source report
     */
    public static SourceReport getWaterReport(int index) {
        return waterReports.get(index);
    }

    /**
     * returns the list of all quality reports
     * @return quality reports list
     */
    public static List<QualityReport> getQualityReports() {
        return qualityReports;
    }

    /**
     * Updates the quality reports list
     * @param qualityReports the new list of quality reports
     */
    public static void setQualityReports(List<QualityReport> qualityReports) {
        References.qualityReports = qualityReports;
    }

    /**
     * add a new quality report to the quality report list
     * @param qualityReport the quality report to be added
     */
    public static void addQualityReport(QualityReport qualityReport) {
        qualityReports.add(numQualityReports++, qualityReport);
    }

    /**
     * returns a given quality report based on its index
     * @param index the index of the desired quality report
     * @return the quality report at the index
     */
    public static QualityReport getQualityReport(int index) {
        return qualityReports.get(index);
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
     * returns apropriate water condition enum for the string
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
