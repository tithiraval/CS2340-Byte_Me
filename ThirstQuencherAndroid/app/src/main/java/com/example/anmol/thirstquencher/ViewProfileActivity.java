package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Controller for viewing profile
 * @author Byte_Me (Team 10)
 * @version 2/20/17
 */
public class ViewProfileActivity extends AppCompatActivity {

    private User user;
    private TextView usernameText;
    private TextView homeAddressText;
    private TextView emailAddressText;
    private TextView titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
        usernameText = (TextView) findViewById(R.id.viewProfileUsername);
        homeAddressText = (TextView) findViewById(R.id.viewProfileHomeAddress);
        emailAddressText = (TextView) findViewById(R.id.viewProfileEmailAddress);
        titleText = (TextView) findViewById(R.id.viewProfileTitle);
        accountTypeText = (TextView) findViewById(R.id.viewProfileAccountType);

        usernameText.setText(user.getUsername());
        homeAddressText.setText(user.getHomeAddress());
        emailAddressText.setText(user.getEmailAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountType().toString());
    }

    /**
     * Returns to the previous screen if the user presses "back"
     * @param view The view for the view profile screen
     */
    public void back(View view) {
        ViewProfileActivity.this.finish();
    }

    /**
     * Method for changing the user's password
     * @param view The view for this screen
     */
    public void changePasswordRequest(View view) {
        Intent intent = new Intent(ViewProfileActivity.this, ChangePasswordActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }
}
