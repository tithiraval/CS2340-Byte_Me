package com.example.anmol.thirstquencher.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Tithi on 3/16/17.
 */

public class QualityReport {
    public static List<String> legalOverallConditions = Arrays.asList("Waste", "Treatable",
            "Untreatable");
    private static int currentReportNumber = 0;
    private Date dateTime;
    private int reportNumber;
    private String emailAddress;
    private String location;
    private OverallCondition condition;
    private double virusPPM;
    private double contaminantPPM;

    /**
     * Creates a new purity report and auto-sets the report Number.
     * @param emailAddress username of the worker submitting the report
     * @param location location of water source
     * @param condition condition the water is in
     */
    public QualityReport(String emailAddress, String location, OverallCondition condition,
                         double virusPPM, double contaminantPPM) {
        this.dateTime = new Date();
        this.location = location;
        this.reportNumber = QualityReport.currentReportNumber;
        QualityReport.currentReportNumber++;
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
     * returns the overall condition
     * @return overall condition
     */
    public OverallCondition getOverallCondition() {return this.condition;}

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
}
