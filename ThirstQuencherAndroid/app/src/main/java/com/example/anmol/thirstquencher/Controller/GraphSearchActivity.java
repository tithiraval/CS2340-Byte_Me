package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;

import com.example.anmol.thirstquencher.R;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Generates the graphs for the graph screen
 * @author Tithi
 * @version 3/28/17
 */
public class GraphSearchActivity extends AppCompatActivity {

    private EditText location;
    private EditText year;
    private Spinner virusOrPPM;
    private Animation animAlpha;
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
                        setContentView(R.layout.activity_graph_search);
                        location = (EditText) findViewById(R.id.graphSearchLocation);
                        year = (EditText) findViewById(R.id.graphSearchYear);
                        virusOrPPM = (Spinner) findViewById(R.id.graphSearchVirusOrPPMSpinner);
                        ArrayAdapter<String> adapter = new ArrayAdapter(GraphSearchActivity.this,
                                android.R.layout.simple_spinner_item,
                                Arrays.asList("Virus PPM", "Contaminant PPM"));
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        virusOrPPM.setAdapter(adapter);
                        animAlpha = AnimationUtils.loadAnimation(GraphSearchActivity.this, R.anim.alpha);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingQualityReports", databaseError.getMessage());
                        Toast.makeText(GraphSearchActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        GraphSearchActivity.this.finish();
                    }
                });
    }


    /**
     * Creates the graph based on input data from the Search Screen
     * @param view The view for this screen
     */
    public void createGraph(View view) {
        Button create = (Button) findViewById(R.id.graphSearchButton);
        create.startAnimation(animAlpha);
        if (location.getText().toString().equals("")) {
            Toast.makeText(GraphSearchActivity.this, "Enter Location!", Toast.LENGTH_SHORT).show();
        } else if (year.getText().toString().equals("")) {
            CharSequence text = "Enter Year!";
            Toast.makeText(GraphSearchActivity.this, "Enter Year!", Toast.LENGTH_SHORT).show();
        } else {
            setContentView(R.layout.activity_view_graph);
            GraphView graph = (GraphView) findViewById(R.id.qualityReportGraph);
            //hash map of month and the list of all values reported for that month
            HashMap<Integer, List<Double>> monthValues= new HashMap<>();
            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            String chosenVal = (String) virusOrPPM.getSelectedItem();
            for (QualityReport report : qualityReports) {
                calendar1.setTime(report.getDateTime());
                calendar2.set(Calendar.YEAR, Integer.parseInt(year.getText().toString()));
                int c1Year = calendar1.get(Calendar.YEAR);
                int c2Year = calendar2.get(Calendar.YEAR);
                if (report.getLocation().equals(location.getText().toString())
                        && (c1Year == c2Year)) {
                    int month = calendar1.get(Calendar.MONTH) + 1;
                    double dataPoint;
                    if (chosenVal.equals("Virus PPM")) {
                        dataPoint = report.getVirusPPM();
                    } else {
                        dataPoint = report.getContaminantPPM();
                    }
                    if (monthValues.containsKey(month)) {
                        monthValues.get(month).add(dataPoint);
                    } else {
                        List<Double> valuesList = new LinkedList<>();
                        valuesList.add(dataPoint);
                        monthValues.put(month, valuesList);
                    }
                }
            }
            if (monthValues.size() == 0) {
                Toast.makeText(GraphSearchActivity.this,
                        "Invalid Location", Toast.LENGTH_SHORT).show();
            }
            List<DataPoint> data = new LinkedList<>();
            for (int key : monthValues.keySet()) {
                if (monthValues.get(key).size() > 1) {
                    double sum = 0;
                    for (double value : monthValues.get(key)) {
                        sum = sum + value;
                    }
                    double average = sum / monthValues.get(key).size();
                    System.out.println(average);
                    data.add(new DataPoint(key, average));
                } else {
                    data.add(new DataPoint(key, monthValues.get(key).get(0)));
                }
            }
            DataPoint[] array = data.toArray(new DataPoint[data.size()]);
            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(array);
            double maxValue = array[0].getY();
            for (DataPoint d : array) {
                if (d.getY() > maxValue) {
                    maxValue = d.getY();
                }
            }
            graph.addSeries(series);
            graph.setTitle(chosenVal + " for " + location.getText().toString()
                    + " in year " + year.getText().toString());
            //graph.setTitleTextSize(50);
            GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Month");
            gridLabel.setVerticalAxisTitle(chosenVal);
            series.setDrawDataPoints(true);
            graph.getViewport().setMinX(0);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxX(12);
            graph.getViewport().setMaxY(maxValue + 100);
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setScrollable(true);
            graph.getViewport().setScrollableY(true);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScrollableY(true);
        }
    }

    /**
     * Returns to the previous screen if the user presses back
     * @param view
     */
    public void back(View view) {
        Button back = (Button) findViewById(R.id.graphSearchCancel);
        back.startAnimation(animAlpha);
        GraphSearchActivity.this.finish();
    }
}
