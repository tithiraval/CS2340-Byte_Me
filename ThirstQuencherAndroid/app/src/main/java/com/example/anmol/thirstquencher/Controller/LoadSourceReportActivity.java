package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Loads a the info of a selected source report
 * @author Anmol
 */
public class LoadSourceReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_source_report);

        FirebaseDatabase.getInstance().getReference(References.SOURCE_REPORT_TABLE)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        References.numSourceReports = (int) dataSnapshot.getChildrenCount();
                        List<SourceReport> sourceReports = new ArrayList<SourceReport>();
                        for (int i = 0; i < References.numSourceReports; i++) {
                            sourceReports.add(i, dataSnapshot.child(Integer.toString(i + 1))
                                    .getValue(SourceReport.class));
                        }
                        References.setWaterReports(sourceReports);
                        Intent intent = new Intent(LoadSourceReportActivity.this,
                                LoadQualityReportActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingSourceReports", databaseError.getMessage());
                        Toast.makeText(LoadSourceReportActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoadSourceReportActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }
}
