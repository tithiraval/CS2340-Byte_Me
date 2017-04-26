package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.transform.Source;

/**
 * Shows a list of all water reports
 * @author Anmol
 * @version 4/25/17
 */
public class WaterReportListActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> displayList;
    private List<SourceReport> sourceReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_source_report);

        FirebaseDatabase.getInstance().getReference(References.SOURCE_REPORT_TABLE)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sourceReports = new ArrayList<SourceReport>();
                        for (int i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                            sourceReports.add(i, dataSnapshot.child(Integer.toString(i + 1))
                                    .getValue(SourceReport.class));
                        }
                        setContentView(R.layout.activity_water_report_list);
                        ListView listView = (ListView) findViewById(R.id.listView1);
                        displayList = new ArrayList<HashMap<String, String>>();
                        for (int i = 0; i < sourceReports.size(); i++) {
                            HashMap<String, String> temp = new HashMap<>();
                            SourceReport sourceReport = sourceReports.get(i);
                            temp.put(References.REPORT_ID, Integer.toString(sourceReport
                                    .getReportNumber()));
                            temp.put(References.DATE_SUBMITTED, sourceReport
                                    .getDateTime().toString());
                            displayList.add(i, temp);
                        }
                        ListViewAdapter adapter = new ListViewAdapter(WaterReportListActivity.this,
                                displayList);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent, final View view,
                                                    int position, long id)
                            {

                                Intent intent = new Intent(WaterReportListActivity.this,
                                        ViewSourceReportActivity.class);
                                References.setCurrentSourceReport(sourceReports.get(position));
                                startActivity(intent);
                            }

                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingSourceReports", databaseError.getMessage());
                        Toast.makeText(WaterReportListActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        WaterReportListActivity.this.finish();
                    }
                });
    }
}