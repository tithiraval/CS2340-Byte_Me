package com.example.anmol.thirstquencher.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.model.SourceReport;
import com.example.anmol.thirstquencher.R;

/**
 * The controller for viewing a source report
 * @author Anmol
 * @version 2/28/17
 */
public class ViewWaterReportActivity extends AppCompatActivity {

    private SourceReport report;
    private TextView dateTimeText;
    private TextView reportNumberText;
    private TextView reporterText;
    private TextView locationText;
    private TextView waterTypeText;
    private TextView waterConditionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_report);
        report = References.getWaterReport(getIntent().getIntExtra("WATER_REPORT_INDEX", 0));
        reportNumberText = (TextView) findViewById(R.id.viewWaterReportNumber);
        dateTimeText = (TextView) findViewById(R.id.viewWaterReportDate);
        reporterText = (TextView) findViewById(R.id.viewWaterReportUsername);
        locationText = (TextView) findViewById(R.id.viewWaterReportLocation);
        waterTypeText = (TextView) findViewById(R.id.viewWaterReportType);
        waterConditionText = (TextView) findViewById(R.id.conditionOfWater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reportNumberText.setText(Integer.toString(report.getReportNumber()));
        dateTimeText.setText(report.getDateTime().toString());
        reporterText.setText(report.getEmailAddress());
        locationText.setText(report.getLocation().getLatLng().toString());
        waterTypeText.setText(report.getTypeVal().toString());
        waterConditionText.setText(report.getConditionVal().toString());
    }

    /**
     * Returns to the previous screen when user presses "Back"
     */
    public void back(@SuppressWarnings("UnusedParameters") View view) {
        ViewWaterReportActivity.this.finish();
    }
}
