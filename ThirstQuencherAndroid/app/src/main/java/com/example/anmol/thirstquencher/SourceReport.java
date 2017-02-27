package com.example.anmol.thirstquencher;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Source Report class to hold a report
 * @author Shaurye
 * @version 2/23/17
 */

public class SourceReport {
    public static List<String> legalWaterTypes = Arrays.asList("Bottled", "Well", "Stream", "Lake",
            "Spring", "Other");
    public static List<String> legalWaterConditions = Arrays.asList("Waste", "Treatable-Clear",
            "Treatable-Muddy", "Portable");
    private static int currentReportNumber = 0;
    private Date dateTime;
    private int reportNumber;
    private final String username;
    private String location;
    private WaterType type;
    private WaterCondition condition;

    /**
     * Creates a new source report and auto-sets the report Number.
     * @param username username of the user submitting the report
     * @param type source of water
     * @param condition condition the water is in
     */
    SourceReport(String username, String location, WaterType type, WaterCondition condition) {
        this.dateTime = new Date();
        this.location = location;
        this.reportNumber = SourceReport.currentReportNumber;
        SourceReport.currentReportNumber++;
        this.username = username;
        this.type = type;
        this.condition = condition;
    }

    /**
     * returns the date on which report was filed
     * @return date
     */
    public Date getDate() { return this.dateTime;}

    /**
     * returns the username of the user that submitted the report
     * @return username
     */
    public String getUsername() { return this.username;}

    /**
     * return the source of the water
     * @return type
     */
    public WaterType getType() {return this.type;}

    /**
     * returns the condition of the water
     * @return condition
     */
    public WaterCondition getCondition() {return this.condition;}

    /**
     * returns the location of the water source
     * @return location
     */
    public String getLocation() {return this.location;}

    /**
     * returns the report number
     * @return reportNumber
     */
    public int getReportNumber() {return this.reportNumber;}

    /**
     * sets the source of water in the report to the new type passed in
     * @param type new source of the water
     */
    public void setType(WaterType type) {this.type = type;}

    /**
     * sets the condition of the water in the report to the new  passed in
     * @param condition new condition of the water
     */
    public void setCondition(WaterCondition condition) {this.condition = condition;}


}
