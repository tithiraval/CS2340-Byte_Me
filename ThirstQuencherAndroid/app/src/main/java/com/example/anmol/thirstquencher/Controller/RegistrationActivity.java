package com.example.anmol.thirstquencher.Controller;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.Admin;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.GeneralUser;
import com.example.anmol.thirstquencher.Model.Manager;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.Model.Worker;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Controller for Registration
 * @author Anmol
 * @version 2/20/17
 */
public class RegistrationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) findViewById(R.id.registerEmail);
        passwordEditText = (EditText) findViewById(R.id.registerPassword);
        userTypeSpinner = (Spinner) findViewById(R.id.registerSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalUserTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
    }

    /**
     * Checks the entered email address and password for restrictions
     * It then creates and account for the user and send an email for verification
     * @param view View of the registration screen
     */
    public void register(View view) {
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (email.equals("")) {
            Toast.makeText(RegistrationActivity.this, "Enter Email Address!",
                    Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(RegistrationActivity.this,
                    "Password should be at least 6 characters long",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Log.d("Regstr", task.getException().getMessage());
                                Toast.makeText(RegistrationActivity.this, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                User user = References.createUser(email, password,
                                        (String) userTypeSpinner.getSelectedItem());
                                if (user == null) {
                                    Toast.makeText(RegistrationActivity.this,
                                            "An error occurred. Please try again!",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    mAuth.getCurrentUser().sendEmailVerification();
                                    FirebaseDatabase.getInstance()
                                            .getReference(References.USER_TABLE)
                                            .child(mAuth.getCurrentUser().getUid()).setValue(user);
                                    Toast.makeText(RegistrationActivity.this, "Account Created!",
                                            Toast.LENGTH_SHORT).show();
                                    RegistrationActivity.this.finish();
                                }
                            }
                        }
                    });
        }
    }

    /**
     * Cancels the registration activity if the user presses cancel
     * @param view The view of the registration screen
     */
    public void cancelRegistration(View view) {
        RegistrationActivity.this.finish();
    }
}
