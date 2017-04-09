package com.example.anmol.thirstquencher.model;

import com.google.firebase.database.Exclude;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Tithi on 3/16/17.
 */

public class QualityReport {
    public static final List<String> legalOverallConditions = Arrays.asList("Waste", "Treatable",
            "Untreatable");
    private Date dateTime;
    private int reportNumber;
    private String emailAddress;
    private String location;
    private OverallCondition condition;
    private double virusPPM;
    private double contaminantPPM;

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


    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public void setCondition(String condition) {
        this.condition = OverallCondition.valueOf(condition);
    }

    @Exclude
    public void setCondition(OverallCondition condition) {
        this.condition = condition;
    }

    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public void setContaminantPPM(double contaminantPPM) {
        this.contaminantPPM = contaminantPPM;
    }
}
