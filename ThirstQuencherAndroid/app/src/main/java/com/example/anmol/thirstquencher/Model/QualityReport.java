package com.example.anmol.thirstquencher.Model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.Exclude;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * The quality report object
 * @author Tithi
 * @version 3/16/17
 */

public class QualityReport {
    public static List<String> legalOverallConditions = Arrays.asList("Waste", "Treatable",
            "Untreatable");
    private Date dateTime;
    private int reportNumber;
    private String emailAddress;
    private String location;
    private OverallCondition condition;
    private double virusPPM;
    private double contaminantPPM;

    /**
     * empty constructor for quality report
     */
    public QualityReport() {}

    /**
     * Creates a new purity report and auto-sets the report Number.
     * @param emailAddress username of the worker submitting the report
     * @param location location of water source
     * @param condition condition the water is in
     */
    public QualityReport(String emailAddress, String location, int reportNumber,OverallCondition condition,
                         double virusPPM, double contaminantPPM) {
        this.dateTime = new Date();
        this.location = location;
        this.reportNumber = reportNumber;
        this.emailAddress = emailAddress;
        this.condition = condition;
        this.virusPPM = virusPPM;
        this.contaminantPPM = contaminantPPM;
    }
    /**
     * returns the date on which report was filed
     * @return date
     */
    public Date getDateTime() { return this.dateTime;}

    /**
     * returns the username of the user that submitted the report
     * @return username
     */
    public String getEmailAddress() { return this.emailAddress;}

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
     * returns the raw string representation of the overall condition
     * @return the raw string representation of the overall condition
     */
    public String getCondition() {return this.condition.name();}

    /**
     * returns the overall condition
     * @return overall condition
     */
    @Exclude
    public OverallCondition getConditionVal() {return this.condition;}

    /**
     * returns the virus ppm
     * @return virus ppm
     */
    public double getVirusPPM() {return this.virusPPM;}

    /**
     * returns the contaminant ppm
     * @return contaminant ppm
     */
    public double getContaminantPPM() {return this.contaminantPPM;}

    /**
     * updates the time and date for the report
     * @param dateTime the new date and time
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * updates email adress
     * @param emailAddress the new email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * updates the location atribute of the report
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * updates the report number
     * @param reportNumber the ID number for the report
     */
    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    /**
     * updates the condition of the water
     * @param condition the new condition of the water
     */
    public void setCondition(String condition) {
        this.condition = OverallCondition.valueOf(condition);
    }

    /**
     * updates the condition of the water
     * @param condition the new condition of the water
     */
    @Exclude
    public void setCondition(OverallCondition condition) {
        this.condition = condition;
    }

    /**
     * updates virus PPM
     * @param virusPPM the virus parts per million concentration
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * updates contaminant PPM
     * @param contaminantPPM the contaminant parts per million concentration
     */
    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
