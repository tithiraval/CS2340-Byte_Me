package com.example.anmol.thirstquencher.Controller;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.Location;
import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;

/**
 * The submit report screen
 * @author Anmol
 */
public class SubmitReportActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private User user;
    private Location location;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;
    private Animation animAlpha;
    private int numReports;
    private GoogleApiClient mGoogleApiClient;
    private int MY_LOCATION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1 &&
                    permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }

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
        Button submit = (Button) findViewById(R.id.submitReportButton);
        submit.startAnimation(animAlpha);
        if (location == null) {
            CharSequence text = "Enter Location!";
            Toast emptyLocation = Toast.makeText(SubmitReportActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyLocation.show();
        } else {
            FirebaseDatabase.getInstance().getReference(References.SOURCE_REPORT_TABLE)
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            SubmitReportActivity.this.numReports =
                                    (int) dataSnapshot.getChildrenCount();
                            final SourceReport report = new SourceReport(user.getEmailAddress(),
                                    SubmitReportActivity.this.location,
                                    SubmitReportActivity.this.numReports + 1,
                                    References.getWaterType((String) waterTypeSpinner.getSelectedItem()),
                                    References.getWaterCondition((String) waterConditionSpinner.getSelectedItem()));
                            FirebaseDatabase.getInstance()
                                    .getReference(References.SOURCE_REPORT_TABLE)
                                    .child(Integer.toString(SubmitReportActivity.this
                                            .numReports + 1)).setValue(report)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(SubmitReportActivity.this,
                                                        "Report Submitted",
                                                        Toast.LENGTH_SHORT).show();
                                                SubmitReportActivity.this.finish();
                                            } else {
                                                Log.e("AddingSourceReport", task.getException()
                                                        .getMessage());
                                                Toast.makeText(SubmitReportActivity.this,
                                                        "An error occured. Please try again!",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.d("LoadingQualityReports", databaseError.getMessage());
                            Toast.makeText(SubmitReportActivity.this,
                                    "An error occured. Please try again!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    /**
     * cancels the report submission and goes back to the previous screen
     * @param view the current view
     */
    public void cancelSubmitReport(View view) {
        Button cancel = (Button) findViewById(R.id.cancelSubmitReportButton);
        cancel.startAnimation(animAlpha);
        SubmitReportActivity.this.finish();
    }
}
