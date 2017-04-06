package com.example.anmol.thirstquencher.Controller;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.Location;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.security.KeyStore;

public class SubmitReportActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private User user;
    private Location location;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        user = References.getCurrentUser();
        waterTypeSpinner = (Spinner) findViewById(R.id.createReportWaterTypeSpinner);
        waterConditionSpinner = (Spinner) findViewById(R.id.createReportWaterConditionSpinner);

        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, SourceReport.legalWaterTypes);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(adapter1);

        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, SourceReport.legalWaterConditions);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterConditionSpinner.setAdapter(adapter2);
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

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                location = new Location(new LatLng(latLng.latitude, latLng.longitude));
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }

    /**
     * Allows the user to submit a report if restrictions are met
     * @param view The view for this screen
     */
    public void submitReport(View view) {
        if (this.isLocationNull(location)) {
            CharSequence text = "Enter Location!";
            Toast emptyLocation = Toast.makeText(SubmitReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyLocation.show();
        } else {
            final SourceReport report = new SourceReport(user.getEmailAddress(), this.location,
                    References.numSourceReports + 1,
                    References.getWaterType((String) waterTypeSpinner.getSelectedItem()),
                    References.getWaterCondition((String) waterConditionSpinner.getSelectedItem()));
            FirebaseDatabase.getInstance()
                    .getReference(References.SOURCE_REPORT_TABLE)
                    .child(Integer.toString(References.numSourceReports + 1)).setValue(report)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        References.addWaterReport(report);
                        Toast.makeText(SubmitReportActivity.this, "Report Submitted",
                                Toast.LENGTH_SHORT).show();
                        SubmitReportActivity.this.finish();
                    } else {
                        Log.e("AddingSourceReport", task.getException().getMessage());
                        Toast.makeText(SubmitReportActivity.this,
                                "An error occurred. Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean isLocationNull (Location someLocation) {
        if (someLocation == null) {
            return true;
        }
        return false;
    }

    public void cancelSubmitReport(View view) {
        SubmitReportActivity.this.finish();
    }
}
