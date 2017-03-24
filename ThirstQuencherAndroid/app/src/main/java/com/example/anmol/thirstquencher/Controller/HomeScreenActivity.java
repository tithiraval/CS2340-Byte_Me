package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;

/**
 * The controller for the Home Screen
 * @author Anmol
 * @version 2/20/17
 */
public class HomeScreenActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
    }

    /**
     * Allows the user to create a report
     * @param view The view for this screen
     */
    public void createReport(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, SubmitReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    public void createQualityReport(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, SubmitQualityReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Allows the user to view list of reports
     * @param view The view for this screen
     */
    public void viewReports(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, WaterReportListActivity.class);
        startActivity(intent);
    }

    public void viewQualityReports(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ViewQualityReportListActivity.class);
        startActivity(intent);
    }

    /**
     * Allows the user to view reports on a map
     * @param view The view for this screen
     */
    public void viewMap(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, MapReportsActivity.class);
        startActivity(intent);
    }

    /**
     * Method for viewing a user's profile information
     * @param view The view for the view profile screen
     */
    public void viewProfile(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ViewProfileActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Method for logging out of the app
     * @param view The screen view
     */
    public void logOut(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
