package com.example.anmol.thirstquencher.Model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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

    public static int numReports;

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
        waterReports.add(numReports++, waterReport);
    }

    public static SourceReport getWaterReport(int index) {
        return waterReports.get(index);
    }



    // Creating a User
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


}
