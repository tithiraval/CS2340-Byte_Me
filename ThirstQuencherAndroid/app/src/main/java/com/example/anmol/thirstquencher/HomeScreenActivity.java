package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.LoginManager;

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
        Intent intent = new Intent(HomeScreenActivity.this, CreateReportActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
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
        if (MainActivity.isLoggedIn()) {
            LoginManager.getInstance().logOut();
        }
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
