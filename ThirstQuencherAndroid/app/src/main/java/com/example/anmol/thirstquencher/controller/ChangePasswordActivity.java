package com.example.anmol.thirstquencher.controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.model.References;
import com.example.anmol.thirstquencher.model.User;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * The class for changing a user's password
 * @author Anmol
 * @version 2/20/17
 */
public class ChangePasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private User user;

    private EditText currentPasswordText;
    private EditText newPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mAuth = FirebaseAuth.getInstance();
        user = References.getCurrentUser();

        currentPasswordText = (EditText) findViewById(R.id.changePasswordEnterCurrent);
        newPasswordText = (EditText) findViewById(R.id.changePasswordEnterNew);
    }

    /**
     * Changes the user's password if matches restrictions
     */
    public void confirmChangePassword(@SuppressWarnings("UnusedParameters") View view) {

        final String currentPassword = currentPasswordText.getText().toString();
        final String newPassword = newPasswordText.getText().toString();

        if (!currentPassword.equals(user.getPassword())) {
            Toast.makeText(ChangePasswordActivity.this, "Incorrect Current Password!",
                    Toast.LENGTH_SHORT).show();
        } else if (newPassword.length() < 6) {
            Toast.makeText(ChangePasswordActivity.this, "New Password cannot be empty!",
                    Toast.LENGTH_SHORT).show();
        } else {
            AuthCredential credential = EmailAuthProvider.getCredential(user.getEmailAddress(),
                    currentPassword);
            mAuth.getCurrentUser().reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        mAuth.getCurrentUser().updatePassword(newPassword)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            if (!mAuth.getCurrentUser().isEmailVerified()){
                                                mAuth.getCurrentUser().sendEmailVerification();
                                            }
                                            FirebaseDatabase.getInstance()
                                                    .getReference(References.USER_TABLE)
                                                    .child(mAuth.getCurrentUser().getUid())
                                                    .child("password").setValue(newPassword);
                                            user.setPassword(newPassword);
                                            Toast.makeText(ChangePasswordActivity.this,
                                                    "Password Successfully Changed",
                                                    Toast.LENGTH_SHORT).show();
                                            ChangePasswordActivity.this.finish();
                                        } else {
                                            Toast.makeText(ChangePasswordActivity.this,
                                                    "An error occurred. Please try again!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(ChangePasswordActivity.this,
                                "An error occurred. Please try again!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Returns back to the previous screen if the user presses cancel.
     */
    public void cancelChangePassword(@SuppressWarnings("UnusedParameters") View view) {
        ChangePasswordActivity.this.finish();
    }}

