package com.example.anmol.thirstquencher.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Source Report class to hold a report
 * @author Shaurye, Anmol
 * @version 4/02/17
 */

public class SourceReport {
    public static List<String> legalWaterTypes = Arrays.asList("Bottled", "Well", "Stream", "Lake",
            "Spring", "Other");
    public static List<String> legalWaterConditions = Arrays.asList("Waste", "Treatable-Clear",
            "Treatable-Muddy", "Portable");
    private Date dateTime;
    private int reportNumber;
    private String emailAddress;
    private Location location;
    private WaterType type;
    private WaterCondition condition;

    /**
     * empty constructor for source report
     */
    public SourceReport() {}

    /**
     * Creates a new source report and auto-sets the report Number.
     * @param emailAddress username of the user submitting the report
     * @param location the location of the water source
     * @param reportNumber the unique identification number of the source report
     * @param type source of water
     * @param condition condition the water is in
     */
    public SourceReport(String emailAddress, Location location, int reportNumber, WaterType type, WaterCondition condition) {
        this.emailAddress = emailAddress;
        this.dateTime = new Date();
        this.location = location;
        this.reportNumber = reportNumber;
        this.type = type;
        this.condition = condition;
    }

    /**
     * returns the email address of the user that submitted the report
     * @return email address
     */
    public String getEmailAddress() { return this.emailAddress;}

    /**
     * returns the date on which report was filed
     * @return date
     */
    public Date getDateTime() { return this.dateTime;}

    /**
     * returns the location of the water source
     * @return location
     */
    public Location getLocation() {return this.location;}

    /**
     * returns the report number
     * @return reportNumber
     */
    public int getReportNumber() {return this.reportNumber;}

    /**
     * return the raw string representation of the type of source of the water
     * @return raw string representation of type
     */
    public String getType() {return this.type.name();}

    /**
     * returns the raw string representation of the condition of the water
     * @return raw string representation of condition
     */
    public String getCondition() {return this.condition.name();}

    /**
     * return the type of source of the water
     * @return type
     */
    @Exclude
    public WaterType getTypeVal() {return this.type;}

    /**
     * returns the condition of the water
     * @return condition
     */
    @Exclude
    public WaterCondition getConditionVal() {return this.condition;}

    /**
     * updates the email address
     * @param emailAddress the new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * updates the date and time
     * @param dateTime the new date and time
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * updates the location
     * @param location the new date and time
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * updates the report number
     * @param reportNumber the new report number
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * updates the water type
     * @param type the new type of the water
     */
    public void setType(String type) {
        this.type = WaterType.valueOf(type);
    }

    /**
     * updates the water condition
     * @param condition the new condition
     */
    public void setCondition(String condition) {
        this.condition = WaterCondition.valueOf(condition);
    }

    /**
     * sets the source of water in the report to the new type passed in
     * @param type new source of the water
     */
    @Exclude
    public void setType(WaterType type) {this.type = type;}

    /**
     * sets the condition of the water in the report to the new  passed in
     * @param condition new condition of the water
     */
    @Exclude
    public void setCondition(WaterCondition condition) {this.condition = condition;}


}
