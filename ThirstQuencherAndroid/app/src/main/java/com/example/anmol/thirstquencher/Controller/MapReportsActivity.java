package com.example.anmol.thirstquencher.Controller;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Handles the map screen
 * @author Anmol
 */
public class MapReportsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_reports);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(SourceReport sr: References.getWaterReports()) {
            String disp = sr.getTypeVal().toString() + ", " + sr.getConditionVal().toString();
            mMap.addMarker(new MarkerOptions().position(sr.getLocation().getLatLng()).title(disp));
        }
    }
}
