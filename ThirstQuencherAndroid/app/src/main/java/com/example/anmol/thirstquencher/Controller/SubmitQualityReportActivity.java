package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.OverallCondition;
import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.maps.model.LatLng;

public class SubmitQualityReportActivity extends AppCompatActivity {

    private User user;
    private EditText location;
    private Spinner overallConditionSpinner;
    private EditText virusPPM;
    private EditText contaminantPPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_quality_report);
        user = References.getCurrentUser();
        overallConditionSpinner = (Spinner) findViewById(R.id.submitQualityReportOverallConditionSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                QualityReport.legalOverallConditions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overallConditionSpinner.setAdapter(adapter);
        location = (EditText) findViewById(R.id.submitQualityReportLocation);
        virusPPM = (EditText) findViewById(R.id.submitQualityReportVirusPPM);
        contaminantPPM = (EditText) findViewById(R.id.submitQualityReportContaminantPPM);

    }

    public void submitQualityReport(View view) {
        if (location.getText().toString().equals("")) {
            CharSequence text = "Enter Location!";
            Toast emptyLocation = Toast.makeText(SubmitQualityReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyLocation.show();
        } else if (virusPPM.getText().toString().equals("")) {
            CharSequence text = "Enter Virus PPM!";
            Toast emptyVirusPPM = Toast.makeText(SubmitQualityReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyVirusPPM.show();
        } else if (contaminantPPM.getText().toString().equals("")) {
            CharSequence text = "Enter Contaminant PPM!";
            Toast emptyContaminantPPM = Toast.makeText(SubmitQualityReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyContaminantPPM.show();
        } else {
            MainActivity.qualityReports.add(0, new QualityReport(user.getUsername(),
                    location.getText().toString(),
                    getOverallCondition((String) overallConditionSpinner.getSelectedItem()),
                    Float.valueOf(virusPPM.getText().toString()),
                    Float.valueOf(contaminantPPM.getText().toString())));
            CharSequence text = "Report Submitted";
            Toast reportSubmitted = Toast.makeText(SubmitQualityReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            reportSubmitted.show();
            SubmitQualityReportActivity.this.finish();
        }
    }

    private OverallCondition getOverallCondition(String condition) {
        if (condition.equals("Safe")) {
            return OverallCondition.SAFE;
        } else if (condition.equals("Treatable")) {
            return OverallCondition.UNSAFE;
        } else {
            return OverallCondition.Treatable;
        }
    }

    public void cancelSubmitQualityReport(View view) {
        SubmitQualityReportActivity.this.finish();
    }
}
