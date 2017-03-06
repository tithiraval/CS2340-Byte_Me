package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;

import javax.xml.transform.Source;

public class ViewWaterReport extends AppCompatActivity {

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
        report = MainActivity.waterReports.get(getIntent().getIntExtra("WATER_REPORT_INDEX", 0));
        reportNumberText = (TextView) findViewById(R.id.viewWaterReportNumberTextField);
        dateTimeText = (TextView) findViewById(R.id.viewWaterReportDateTextField);
        reporterText = (TextView) findViewById(R.id.viewWaterReportUsernameTextField);
        locationText = (TextView) findViewById(R.id.viewWaterReportLocationTextField);
        waterTypeText = (TextView) findViewById(R.id.viewWaterReportTypeTextField);
        waterConditionText = (TextView) findViewById(R.id.conditionOfWaterTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reportNumberText.setText(report.getReportNumber());
        dateTimeText.setText(report.getDate().toString());
        reporterText.setText(report.getUsername());
        locationText.setText(report.getLocation());
        waterTypeText.setText(report.getType().toString());
        waterConditionText.setText(report.getCondition().toString());
    }

    public void back(View view) {
        ViewWaterReport.this.finish();
    }
}
