package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.R;

/**
 * Shows a detailed highlight of a quality report
 * @author Anmol, Tithi
 * @version 4/25/17
 */
public class ViewQualityReportActivity extends AppCompatActivity {

    private QualityReport report;
    private TextView dateTimeText;
    private TextView reportNumberText;
    private TextView reporterText;
    private TextView locationText;
    private TextView overallConditionText;
    private TextView virusPPMText;
    private TextView contaminantPPMText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quality_report);
        report = References.getCurrentQualityReport();
        reportNumberText = (TextView) findViewById(R.id.viewQualityReportIDTextField);
        dateTimeText = (TextView) findViewById(R.id.viewQualityReportDateTextField);
        reporterText = (TextView) findViewById(R.id.viewQualityReportUserTextField);
        locationText = (TextView) findViewById(R.id.viewQualityReportLocationTextView);
        overallConditionText = (TextView) findViewById(R.id.viewQualityReportOverallConditionTextView);
        contaminantPPMText = (TextView) findViewById(R.id.viewQualityReportContaminantPPMTextView);
        virusPPMText = (TextView) findViewById(R.id.viewQualityReportVirusPPMTextView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        reportNumberText.setText(Integer.toString(report.getReportNumber()));
        dateTimeText.setText(report.getDateTime().toString());
        reporterText.setText(report.getEmailAddress());
        locationText.setText(report.getLocation());
        overallConditionText.setText(report.getConditionVal().toString());
        virusPPMText.setText(Double.toString(report.getVirusPPM()));
        contaminantPPMText.setText(Double.toString(report.getContaminantPPM()));
    }

    /**
     * Returns to the previous screen when user presses "Back"
     * @param view The back button
     */
    public void goBack(View view) {
        ViewQualityReportActivity.this.finish();
    }
}
