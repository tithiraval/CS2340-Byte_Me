package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the map screen
 * @author Anmol
 */
public class MapReportsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
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
                        setContentView(R.layout.activity_map_reports);
                        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.map);
                        mapFragment.getMapAsync(MapReportsActivity.this);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d("LoadingSourceReports", databaseError.getMessage());
                        Toast.makeText(MapReportsActivity.this,
                                "An error occured. Please try again!",
                                Toast.LENGTH_SHORT).show();
                        MapReportsActivity.this.finish();
                    }
                });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(SourceReport sr: sourceReports) {
            String disp = sr.getTypeVal().toString() + ", " + sr.getConditionVal().toString();
            mMap.addMarker(new MarkerOptions().position(sr.getLocation().getLatLng()).title(disp));
        }
    }
}
