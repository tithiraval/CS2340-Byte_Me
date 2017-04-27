package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;

public class ViewSourceReportActivity extends AppCompatActivity {

    private SourceReport report;
    private TextView dateTimeText;
    private TextView reportNumberText;
    private TextView reporterText;
    private TextView waterTypeText;
    private TextView waterConditionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_source_report);
        report = References.getCurrentSourceReport();
        reportNumberText = (TextView) findViewById(R.id.viewSourceReportID);
        dateTimeText = (TextView) findViewById(R.id.viewSourceReportDate);
        reporterText = (TextView) findViewById(R.id.viewSourceReportUser);
        waterTypeText = (TextView) findViewById(R.id.viewSourceReportWaterType);
        waterConditionText = (TextView) findViewById(R.id.viewSourceReportWaterCondition);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reportNumberText.setText(Integer.toString(report.getReportNumber()));
        dateTimeText.setText(report.getDateTime().toString());
        reporterText.setText(report.getEmailAddress());
        waterTypeText.setText(report.getType().toString());
        waterConditionText.setText(report.getCondition().toString());
    }

    public void seeLocation(View view) {
        Intent intent = new Intent(ViewSourceReportActivity.this, ViewSourceReportLocationActivity.class);
        startActivity(intent);
    }

    /**
     * Returns to the previous screen when user presses "Back"
     * @param view The back button
     */
    public void back(View view) {
        ViewSourceReportActivity.this.finish();
    }

}
