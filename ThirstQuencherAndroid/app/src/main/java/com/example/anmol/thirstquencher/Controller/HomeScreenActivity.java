package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.anmol.thirstquencher.Model.Admin;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.GeneralUser;
import com.example.anmol.thirstquencher.Model.Manager;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.Model.UserType;
import com.example.anmol.thirstquencher.Model.Worker;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The controller for the Home Screen
 * @author Anmol
 * @version 2/20/17
 */
public class HomeScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private User user;
    private Animation animAlpha;
    private Button createQRButton;
    private Button viewQRButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        mAuth = FirebaseAuth.getInstance();
        user = References.getCurrentUser();

        Log.i("User", user.getAccountType());

        createQRButton = (Button) findViewById(R.id.createQualityReportButton);
        viewQRButton = (Button) findViewById(R.id.viewQualityReportButton);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

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
     * @param view The view for this screen
     */
    public void createReport(View view) {
        Button createReportButton = (Button) findViewById(R.id.createReportButton);
        createReportButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, SubmitReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Allows either a worker or a manager to create a quality report
     * @param view The view for this screen
     */
    public void createQualityReport(View view) {
        Button qReportButton = (Button) findViewById(R.id.createQualityReportButton);
        qReportButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, SubmitQualityReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Allows the user to view list of reports
     * @param view The view for this screen
     */
    public void viewReports(View view) {
        Button viewReportsButton = (Button) findViewById(R.id.viewReportsButton);
        viewReportsButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, WaterReportListActivity.class);
        startActivity(intent);
    }

    /**
     * Alows user to view list of quality reports
     * @param view The view for this screen
     */
    public void viewQualityReports(View view) {
        Button viewQReportsButton = (Button) findViewById(R.id.viewQualityReportButton);
        viewQReportsButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, ViewQualityReportListActivity.class);
        startActivity(intent);
    }

    /**
     * Alows user to view list of quality reports
     * @param view The view for this screen
     */
    public void graphSearch(View view) {
        Button graphSearchButton = (Button) findViewById(R.id.viewQualityReportGraph);
        graphSearchButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, GraphSearchActivity.class);
        startActivity(intent);
    }

    /**
     * Allows the user to view reports on a map
     * @param view The view for this screen
     */
    public void viewMap(View view) {
        Button viewMapButton = (Button) findViewById(R.id.viewReportsMapButton);
        viewMapButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, MapReportsActivity.class);
        startActivity(intent);
    }

    /**
     * Method for viewing a user's profile information
     * @param view The view for the view profile screen
     */
    public void viewProfile(View view) {
        Button viewProfileButton = (Button) findViewById(R.id.viewProfileButton);
        viewProfileButton.startAnimation(animAlpha);
        Intent intent = new Intent(HomeScreenActivity.this, ViewProfileActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    /**
     * Method for logging out of the app
     * @param view The screen view
     */
    public void logOut(View view) {
        Button logout = (Button) findViewById(R.id.logoutButton);
        logout.startAnimation(animAlpha);
        mAuth.signOut();
        References.setCurrentUser((User) null);
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
