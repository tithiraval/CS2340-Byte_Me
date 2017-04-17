package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads a the info of a selected quality report
 * @author Anmol
 * @version 2/20/17
 */
public class LoadQualityReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_quality_report);

        FirebaseDatabase.getInstance().getReference(References.QUALITY_REPORT_TABLE)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        References.numQualityReports = (int) dataSnapshot.getChildrenCount();
                        List<QualityReport> qualityReports = new ArrayList<QualityReport>();
                        for (int i = 0; i < References.numQualityReports; i++) {
                            qualityReports.add(i, dataSnapshot.child(Integer.toString(i + 1))
                                    .getValue(QualityReport.class));
                        }
                        References.setQualityReports(qualityReports);
                        Intent intent = new Intent(LoadQualityReportActivity.this,
                                HomeScreenActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingQualityReports", databaseError.getMessage());
                        Toast.makeText(LoadQualityReportActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoadQualityReportActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }
}
