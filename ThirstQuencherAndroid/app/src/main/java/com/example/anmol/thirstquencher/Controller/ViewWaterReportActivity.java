package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
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
        waterTypeText.setText(report.getType().toString());
        waterConditionText.setText(report.getCondition().toString());
    }

    /**
     * Returns to the previous screen when user presses "Back"
     * @param view The view for this screen
     */
    public void back(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Button back = (Button) findViewById(R.id.viewWaterReportBackButton);
        back.startAnimation(animAlpha);
        ViewWaterReportActivity.this.finish();
    }
}
