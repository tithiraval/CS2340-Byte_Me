package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * The controller for editing a profile
 * @author Anmol
 * @version 2/23/17
 */
public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private User user;
    private Animation animAlpha;
    private TextView emailAddressText;
    private EditText usernameText;
    private EditText homeAddressText;
    private EditText titleText;
    private TextView accountTypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        user = References.getCurrentUser();
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);


        emailAddressText = (TextView) findViewById(R.id.editProfileEmailAddress);
        usernameText = (EditText) findViewById(R.id.editProfileUsername);
        homeAddressText = (EditText) findViewById(R.id.editProfileHomeAddress);
        titleText = (EditText) findViewById(R.id.editProfileTitle);
        accountTypeText = (TextView) findViewById(R.id.editProfileAccountType);

        emailAddressText.setText(user.getEmailAddress());
        usernameText.setText(user.getUsername());
        homeAddressText.setText(user.getHomeAddress());
        titleText.setText(user.getTitle());
        accountTypeText.setText(user.getAccountTypeVal().toString());
    }

    /**
     * Sets the home address, email address, and the title of the user
     * @param view The button for saving the edited profile
     */
    public void editProfileSaveRequest(View view) {
        Button save = (Button) findViewById(R.id.editProfileSaveButton);
        save.startAnimation(animAlpha);
        final String username = usernameText.getText().toString();
        final String homeAddress = homeAddressText.getText().toString();
        final String title = titleText.getText().toString();

        FirebaseDatabase.getInstance().getReference(References.USER_TABLE)
                .child(mAuth.getCurrentUser().getUid())
                .updateChildren(user.updateProfile(username, homeAddress, title),
                        new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        if (databaseError == null) {
                            user.setUsername(username);
                            user.setHomeAddress(homeAddress);
                            user.setTitle(title);
                            Toast.makeText(EditProfileActivity.this, "Changes Saved",
                                    Toast.LENGTH_SHORT).show();
                            EditProfileActivity.this.finish();
                        } else {
                            Log.e("Database", databaseError.getMessage());
                            Toast.makeText(EditProfileActivity.this,
                                    "An error occured. Please try again!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * Returns to the user screen if the user presses cancel
     * @param view The button for cancelling edit profile
     */
    public void cancelEditProfile(View view) {
        Button cancel = (Button) findViewById(R.id.editProfileCancelButton);
        cancel.startAnimation(animAlpha);
        EditProfileActivity.this.finish();
    }
}
