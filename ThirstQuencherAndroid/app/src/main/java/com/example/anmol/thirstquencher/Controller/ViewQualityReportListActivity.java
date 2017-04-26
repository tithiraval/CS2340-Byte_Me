package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Shows a list of all the quality reports
 * @author Anmol
 * @version 2/20/17
 */
public class ViewQualityReportListActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> displayList;
    private List<QualityReport> qualityReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_quality_report);

        FirebaseDatabase.getInstance().getReference(References.QUALITY_REPORT_TABLE)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        qualityReports = new ArrayList<QualityReport>();
                        for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                            qualityReports.add(i, dataSnapshot.child(Integer.toString(i + 1))
                                    .getValue(QualityReport.class));
                        }
                        setContentView(R.layout.activity_view_quality_report_list);
                        ListView listView = (ListView) findViewById(R.id.qualityReportListView);
                        displayList = new ArrayList<HashMap<String, String>>();
                        for (int i = 0; i < qualityReports.size(); i++) {
                            HashMap<String, String> temp = new HashMap<>();
                            QualityReport qualityReport = qualityReports.get(i);
                            temp.put(References.REPORT_ID, Integer.toString(qualityReport.getReportNumber()));
                            temp.put(References.DATE_SUBMITTED, qualityReport.getDateTime().toString());
                            displayList.add(i, temp);
                        }
                        ListViewAdapter adapter = new ListViewAdapter(ViewQualityReportListActivity.this, displayList);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
                            {


                                Intent intent = new Intent(ViewQualityReportListActivity.this, ViewQualityReportActivity.class);
                                References.setCurrentQualityReport(qualityReports.get(position));
                                startActivity(intent);
                            }

                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingQualityReports", databaseError.getMessage());
                        Toast.makeText(ViewQualityReportListActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        ViewQualityReportListActivity.this.finish();
                    }
                });
    }
}