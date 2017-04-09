package com.example.anmol.thirstquencher.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.model.User;
import com.example.anmol.thirstquencher.R;

/**
 * Controller for viewing profile
 * @author Anmol
 * @version 2/20/17
 */
public class ViewProfileActivity extends AppCompatActivity {

    private User user;

    private TextView emailAddressText;
    private TextView usernameText;
    private TextView homeAddressText;
    private TextView titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        user = References.getCurrentUser();
        emailAddressText = (TextView) findViewById(R.id.viewProfileEmailAddress);
        usernameText = (TextView) findViewById(R.id.viewProfileUsername);
        homeAddressText = (TextView) findViewById(R.id.viewProfileHomeAddress);
        titleText = (TextView) findViewById(R.id.viewProfileTitle);
        accountTypeText = (TextView) findViewById(R.id.viewProfileAccountType);
    }

    @Override
    protected void onResume() {
        super.onResume();
        usernameText.setText(user.getUsername());
        homeAddressText.setText(user.getHomeAddress());
        emailAddressText.setText(user.getEmailAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountTypeVal().toString());
    }

    /**
     * Prompts to edit the user's profile
     */
    public void editProfileRequest(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Method for changing the user's password
     */
    public void changePasswordRequest(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(ViewProfileActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * Returns to the previous screen if the user presses "back"
     */
    public void back(@SuppressWarnings("UnusedParameters") View view) {
        ViewProfileActivity.this.finish();
    }
}
