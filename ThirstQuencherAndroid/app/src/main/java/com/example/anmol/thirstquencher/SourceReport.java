package com.example.anmol.thirstquencher;

import java.util.Date;

/**
 * Created by Shaurye on 2/23/17.
 */

public class SourceReport {
    private static int currentReportNumber = 0;
    private Date dateTime;
    private int reportNumber;
    private final String username;
    private String location;
    private WaterType type;
    private WaterCondition condition;

    SourceReport(String username, WaterType type, WaterCondition condition) {
        dateTime = new Date();
        this.location = "";
        reportNumber = SourceReport.currentReportNumber;
        SourceReport.currentReportNumber++;
        this.username = username;
        this.type = type;
        this.condition = condition;
    }

    public Date getDate() { return this.dateTime;}
    public String getUsername() { return this.username;}
    public WaterType getType() {return this.type;}
    public WaterCondition getCondition() {return this.condition;}
    public String getLocation() {return this.location;}
    public int getReportNumber() {return this.reportNumber;}

    public void setType(WaterType type) {this.type = type;}
    public void setCondition(WaterCondition condition) {this.condition = condition;}


}
