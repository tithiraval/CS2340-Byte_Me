package com.example.anmol.thirstquencher.Controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

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
            final QualityReport qualityReport = new QualityReport(user.getEmailAddress(),
                    location.getText().toString(), References.numQualityReports + 1,
                    References.getCondition((String) overallConditionSpinner.getSelectedItem()),
                    Double.valueOf(virusPPM.getText().toString()),
                    Double.valueOf(contaminantPPM.getText().toString()));
            FirebaseDatabase.getInstance()
                    .getReference(References.QUALITY_REPORT_TABLE)
                    .child(Integer.toString(References.numQualityReports + 1)).setValue(qualityReport)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                References.addQualityReport(qualityReport);
                                Toast.makeText(SubmitQualityReportActivity.this, "Report Submitted",
                                        Toast.LENGTH_SHORT).show();
                                SubmitQualityReportActivity.this.finish();
                            } else {
                                Log.e("AddingSourceReport", task.getException().getMessage());
                                Toast.makeText(SubmitQualityReportActivity.this,
                                        "An error occured. Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    public void cancelSubmitQualityReport(View view) {
        SubmitQualityReportActivity.this.finish();
    }
}
