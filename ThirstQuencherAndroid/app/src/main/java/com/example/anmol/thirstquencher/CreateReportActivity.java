package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * The controller for creating a water report
 * @author Anmol
 * @version 2/20/17
 */
public class CreateReportActivity extends AppCompatActivity {

    private User user;
    private EditText location;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_report);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
        location = (EditText) findViewById(R.id.createReportEnterLocation);
        waterTypeSpinner = (Spinner) findViewById(R.id.createReportWaterTypeSpinner);
        waterConditionSpinner = (Spinner) findViewById(R.id.createReportWaterConditionSpinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, SourceReport.legalWaterTypes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, SourceReport.legalWaterConditions);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterConditionSpinner.setAdapter(adapter2);
    }

    /**
     * Allows the user to submit a report if restrictions are met
     * @param view The view for this screen
     */
    public void submitReport(View view) {
        String locationText = location.getText().toString();
        if (locationText.equals("")) {
            CharSequence text = "Enter Location!";
            Toast emptyLocation = Toast.makeText(CreateReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyLocation.show();
        } else {
            MainActivity.waterReports.add(0, new SourceReport(user.getUsername(), locationText,
                    getWaterType((String) waterTypeSpinner.getSelectedItem()),
                    getWaterCondition((String) waterConditionSpinner.getSelectedItem())));
            CharSequence text = "Report Submitted";
            Toast reportSubmitted = Toast.makeText(CreateReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            reportSubmitted.show();
            CreateReportActivity.this.finish();
        }
    }

    /**
     * Gets the type of the water source
     * @param waterType The type of the water source
     * @return
     */
    private WaterType getWaterType(String waterType) {
        if (waterType.equals("Bottled")) {
            return WaterType.BOTTLED;
        } else if (waterType.equals("Well")) {
            return WaterType.WELL;
        } else if (waterType.equals("Stream")) {
            return WaterType.STREAM;
        } else if (waterType.equals("Lake")) {
            return WaterType.LAKE;
        } else if (waterType.equals("Spring")) {
            return WaterType.SPRING;
        } else {
            return WaterType.OTHER;
        }
    }

    private WaterCondition getWaterCondition(String waterCondition) {
        if (waterCondition.equals("Waste")) {
            return WaterCondition.WASTE;
        } else if (waterCondition.equals("Treatable-Clear")) {
            return WaterCondition.TREATABLE_CLEAR;
        } else if (waterCondition.equals("Treatable-Muddy")) {
            return WaterCondition.TREATABLE_MUDDY;
        } else {
            return WaterCondition.PORTABLE;
        }
    }

    public void cancelSubmitReport(View view) {
        CreateReportActivity.this.finish();
    }
}
