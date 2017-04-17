package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Shows a list of all the quality reports
 * @author Anmol
 */
public class ViewQualityReportListActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> displayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_quality_report_list);
        ListView listView = (ListView) findViewById(R.id.qualityReportListView);
        displayList = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < References.getQualityReports().size(); i++) {
            HashMap<String, String> temp = new HashMap<>();
            QualityReport qualityReport = References.getQualityReport(i);
            temp.put(References.REPORT_ID, Integer.toString(qualityReport.getReportNumber()));
            temp.put(References.DATE_SUBMITTED, qualityReport.getDateTime().toString());
            displayList.add(i, temp);
        }
        ListViewAdapter adapter = new ListViewAdapter(this, displayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {

                Intent intent = new Intent(ViewQualityReportListActivity.this, ViewQualityReportActivity.class);
                intent.putExtra("QUALITY_REPORT_INDEX", position);
                startActivity(intent);
            }

        });
    }
}