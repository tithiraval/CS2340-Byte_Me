package com.example.anmol.thirstquencher.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.model.SourceReport;
import com.example.anmol.thirstquencher.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WaterReportListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_report_list);
        ListView listView = (ListView) findViewById(R.id.listView1);
        ArrayList<HashMap<String, String>> displayList = new ArrayList<>();
        for (int i = 0; i < References.getWaterReports().size(); i++) {
            HashMap<String, String> temp = new HashMap<>();
            SourceReport sourceReport = References.getWaterReport(i);
            temp.put(References.REPORT_ID, Integer.toString(sourceReport.getReportNumber()));
            temp.put(References.DATE_SUBMITTED, sourceReport.getDateTime().toString());
            displayList.add(i, temp);
        }
        ListViewAdapter adapter = new ListViewAdapter(this, displayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {

                Intent intent = new Intent(WaterReportListActivity.this, ViewWaterReportActivity.class);
                intent.putExtra("WATER_REPORT_INDEX", position);
                startActivity(intent);
            }

        });
    }
}