package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * The controller for editing a profile
 * @author Anmol
 * @version 2/23/17
 */
public class EditProfileActivity extends AppCompatActivity {

    private User user;
    private TextView usernameText;
    private EditText homeAddressText;
    private EditText emailAddressText;
    private EditText titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
        usernameText = (TextView) findViewById(R.id.editProfileUsername);
        homeAddressText = (EditText) findViewById(R.id.editProfileHomeAddress);
        emailAddressText = (EditText) findViewById(R.id.editProfileEmailAddress);
        titleText = (EditText) findViewById(R.id.editProfileTitle);
        accountTypeText = (TextView) findViewById(R.id.editProfileAccountType);

        usernameText.setText(user.getUsername());
        homeAddressText.setText(user.getHomeAddress());
        emailAddressText.setText(user.getEmailAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountType().toString());
    }

    /**
     * Sets the home address, email address, and the title of the user
     * @param view The view for editing profile
     */
    public void editProfileSaveRequest(View view) {
        String homeAddress = homeAddressText.getText().toString();
        String emailAddress = emailAddressText.getText().toString();
        String title = titleText.getText().toString();

        user.setHomeAddress(homeAddress);
        user.setEmailAddress(emailAddress);
        user.setTitle(title);

        CharSequence text = "Changes Saved";
        Toast saveChanges = Toast.makeText(EditProfileActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
        saveChanges.show();

        EditProfileActivity.this.finish();
    }

    /**
     * Returns to the user screen if the user presses cancel
     * @param view The view for edit profile
     */
    public void cancelEditProfile(View view) {
        EditProfileActivity.this.finish();
    }
}
