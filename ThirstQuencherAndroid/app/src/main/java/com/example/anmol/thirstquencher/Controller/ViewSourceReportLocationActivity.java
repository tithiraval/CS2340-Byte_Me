package com.example.anmol.thirstquencher.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * The controller for the view source report screen
 * @author Anmol
 * @version 2/20/17
 */
public class ViewSourceReportLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SourceReport report;
    private TextView dateTimeText;
    private TextView reportNumberText;
    private TextView reporterText;
    private TextView waterTypeText;
    private TextView waterConditionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_source_report_location);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        report = References.getCurrentSourceReport();
        reportNumberText = (TextView) findViewById(R.id.viewWaterReportNumber);
        dateTimeText = (TextView) findViewById(R.id.viewWaterReportDate);
        reporterText = (TextView) findViewById(R.id.viewWaterReportUsername);
        waterTypeText = (TextView) findViewById(R.id.viewWaterReportType);
        waterConditionText = (TextView) findViewById(R.id.conditionOfWater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reportNumberText.setText(Integer.toString(report.getReportNumber()));
        dateTimeText.setText(report.getDateTime().toString());
        reporterText.setText(report.getEmailAddress());
        waterTypeText.setText(report.getType().toString());
        waterConditionText.setText(report.getCondition().toString());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng loc = report.getLocation().getLatLng();
        mMap.addMarker(new MarkerOptions().position(loc));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }

    /**
     * Returns to the previous screen when user presses "Back"
     * @param view The back button
     */
    public void back(View view) {
        ViewSourceReportLocationActivity.this.finish();
    }
}
