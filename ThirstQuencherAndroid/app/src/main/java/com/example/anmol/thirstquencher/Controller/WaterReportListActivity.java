package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.anmol.thirstquencher.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WaterReportListActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> displayList;
    public final static String REPORT_ID = "Report ID";
    public final static String DATE_SUBMITTED = "Date Submitted";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_report_list);

        ListView listView = (ListView) findViewById(R.id.listView1);

        displayList = new ArrayList<HashMap<String,String>>();

        for (int i = 0; i < MainActivity.waterReports.size(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put(WaterReportListActivity.REPORT_ID
                    , Integer.toString(MainActivity.waterReports.get(i).getReportNumber()));
            temp.put(WaterReportListActivity.DATE_SUBMITTED
                    , MainActivity.waterReports.get(i).getDate().toString());
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
//                int pos=position+1;
//                Toast.makeText(WaterReportListActivity.this, Integer.toString(pos) + " Clicked"
//                        , Toast.LENGTH_SHORT).show();
            }

        });
    }
}