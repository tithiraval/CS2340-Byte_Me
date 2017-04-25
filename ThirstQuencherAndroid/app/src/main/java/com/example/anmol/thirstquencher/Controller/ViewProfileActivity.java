package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;

/**
 * Controller for viewing profile
 * @author Anmol
 * @version 2/20/17
 */
public class ViewProfileActivity extends AppCompatActivity {

    private User user;
    private Animation animAlpha;
    private TextView emailAddressText;
    private TextView usernameText;
    private TextView homeAddressText;
    private TextView titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

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
     * @param view The view for this screen
     */
    public void editProfileRequest(View view) {
        Button edit = (Button) findViewById(R.id.viewProfileEditButton);
        edit.startAnimation(animAlpha);
        Intent intent = new Intent(ViewProfileActivity.this, EditProfileActivity.class);
        startActivity(intent);
    }

    /**
     * Method for changing the user's password
     * @param view The view for this screen
     */
    public void changePasswordRequest(View view) {
        Button change = (Button) findViewById(R.id.viewProfileChangePasswordButton);
        change.startAnimation(animAlpha);
        Intent intent = new Intent(ViewProfileActivity.this, ChangePasswordActivity.class);
        startActivity(intent);
    }

    /**
     * Returns to the previous screen if the user presses "back"
     * @param view The view for the view profile screen
     */
    public void back(View view) {
        Button back = (Button) findViewById(R.id.viewProfileBackButton);
        back.startAnimation(animAlpha);
        ViewProfileActivity.this.finish();
    }
}
