package com.example.anmol.thirstquencher.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.model.Manager;
import com.example.anmol.thirstquencher.model.User;
import com.example.anmol.thirstquencher.model.Worker;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The controller for the Home Screen
 * @author Anmol
 * @version 2/20/17
 */
public class HomeScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();
        user = References.getCurrentUser();

        Button createQRButton;
        Button viewQRButton;

        Log.i("User", user.getAccountType());

        createQRButton = (Button) findViewById(R.id.createQualityReportButton);
        viewQRButton = (Button) findViewById(R.id.viewQualityReportButton);


        if (user instanceof Worker) {
            createQRButton.setVisibility(View.VISIBLE);
            viewQRButton.setVisibility(View.VISIBLE);
        }

        if (user instanceof Manager) {
            findViewById(R.id.viewQualityReportGraph).setVisibility(View.VISIBLE);
        }

    }

    /**
     * Allows the user to create a report
     */
    public void createReport(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, SubmitReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    public void createQualityReport(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, SubmitQualityReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Allows the user to view list of reports
     */
    public void viewReports(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, WaterReportListActivity.class);
        startActivity(intent);
    }

    /**
     * Allows user to view list of quality reports
     */
    public void viewQualityReports(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ViewQualityReportListActivity.class);
        startActivity(intent);
    }

    /**
     * Allows user to view list of quality reports
     */
    public void graphSearch(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, GraphSearchActivity.class);
        startActivity(intent);
    }

    /**
     * Allows the user to view reports on a map
     */
    public void viewMap(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, MapReportsActivity.class);
        startActivity(intent);
    }

    /**
     * Method for viewing a user's profile information
     */
    public void viewProfile(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ViewProfileActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Method for logging out of the app
     */
    public void logOut(@SuppressWarnings("UnusedParameters") View view) {
        mAuth.signOut();
        References.setCurrentUser(null);
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
