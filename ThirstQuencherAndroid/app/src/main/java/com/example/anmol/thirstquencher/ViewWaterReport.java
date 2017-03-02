package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.xml.transform.Source;

public class ViewWaterReport extends AppCompatActivity {

    private SourceReport report;
    private TextView usernameText;
    private TextView homeAddressText;
    private TextView emailAddressText;
    private TextView titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_water_report);

        report = MainActivity.waterReports.get(getIntent().getIntExtra("WATER_REPORT_INDEX", 0));
        usernameText = (TextView) findViewById(R.id.viewProfileUsername);
        homeAddressText = (TextView) findViewById(R.id.viewProfileHomeAddress);
        emailAddressText = (TextView) findViewById(R.id.viewProfileEmailAddress);
        titleText = (TextView) findViewById(R.id.viewProfileTitle);
        accountTypeText = (TextView) findViewById(R.id.viewProfileAccountType);
    }
}
