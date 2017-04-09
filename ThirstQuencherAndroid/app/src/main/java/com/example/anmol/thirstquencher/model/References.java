package com.example.anmol.thirstquencher.model;

import java.util.List;

/**
 * Created by Anmol on 3/28/17.
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

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }



    public static List<SourceReport> getWaterReports() {
        return waterReports;
    }

    public static void setWaterReports(List<SourceReport> waterReports) {
        References.waterReports = waterReports;
    }

    public static void addWaterReport(SourceReport waterReport) {
        waterReports.add(numSourceReports++, waterReport);
    }

    public static SourceReport getWaterReport(int index) {
        return waterReports.get(index);
    }



    public static List<QualityReport> getQualityReports() {
        return qualityReports;
    }

    public static void setQualityReports(List<QualityReport> qualityReports) {
        References.qualityReports = qualityReports;
    }

    public static void addQualityReport(QualityReport qualityReport) {
        qualityReports.add(numQualityReports++, qualityReport);
    }

    public static QualityReport getQualityReport(int index) {
        return qualityReports.get(index);
    }



    // Creating a User
    public static User createUser(String email, String password, String typeOfUser) {
        switch (typeOfUser) {
            case "Manager":
                return new Manager(email, password);
            case "Worker":
                return new Worker(email, password);
            case "Admin":
                return new Admin(email, password);
            case "User":
                return new GeneralUser(email, password);
        }
        return null;
    }

    /**
     * Gets the type of the water source
     * @param waterType The string representation of the type of the water source
     * @return the type of the water source
     */
    public static WaterType getWaterType(String waterType) {
        switch (waterType) {
            case "Bottled":
                return WaterType.BOTTLED;
            case "Well":
                return WaterType.WELL;
            case "Stream":
                return WaterType.STREAM;
            case "Lake":
                return WaterType.LAKE;
            case "Spring":
                return WaterType.SPRING;
            default:
                return WaterType.OTHER;
        }
    }

    public static WaterCondition getWaterCondition(String waterCondition) {
        switch (waterCondition) {
            case "Waste":
                return WaterCondition.WASTE;
            case "Treatable-Clear":
                return WaterCondition.TREATABLE_CLEAR;
            case "Treatable-Muddy":
                return WaterCondition.TREATABLE_MUDDY;
            default:
                return WaterCondition.PORTABLE;
        }
    }

    public static OverallCondition getCondition(String condition) {
        switch (condition) {
            case "Safe":
                return OverallCondition.SAFE;
            case "Treatable":
                return OverallCondition.UNSAFE;
            default:
                return OverallCondition.Treatable;
        }
    }


}
