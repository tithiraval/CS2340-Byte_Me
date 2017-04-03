package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.jjoe64.graphview.GraphView;

import com.example.anmol.thirstquencher.R;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Arrays;

public class GraphSearchActivity extends AppCompatActivity {

    private EditText location;
    private EditText year;
    private Spinner virusOrPPM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_search);
        location = (EditText) findViewById(R.id.graphSearchLocation);
        year = (EditText) findViewById(R.id.graphSearchYear);
        virusOrPPM = (Spinner) findViewById(R.id.graphSearchVirusOrPPMSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                Arrays.asList("Virus", "Contaminant PPM"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        virusOrPPM.setAdapter(adapter);
    }


    public void createGraph(View view) {
        setContentView(R.layout.activity_view_graph);
        GraphView graph = (GraphView) findViewById(R.id.qualityReportGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        String chosenVal = (String) virusOrPPM.getSelectedItem();
        //series.setThickness(8);
        graph.addSeries(series);
        graph.setTitle(chosenVal + " for " + location.getText().toString()
                + " in year " + year.getText().toString());
        //graph.setTitleTextSize(50); //may need to be bigger
        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Year");
        gridLabel.setVerticalAxisTitle(chosenVal);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollableY(true);
    }

    public void back(View view) {
        GraphSearchActivity.this.finish();
    }
}
