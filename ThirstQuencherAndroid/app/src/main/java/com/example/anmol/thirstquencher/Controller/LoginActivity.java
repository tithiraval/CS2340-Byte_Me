package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.Admin;
import com.example.anmol.thirstquencher.Model.GeneralUser;
import com.example.anmol.thirstquencher.Model.Manager;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.UserType;
import com.example.anmol.thirstquencher.Model.Worker;
import com.example.anmol.thirstquencher.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The controller for logging in
 * @author Anmol
 * @version 2/20/17
 */
public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        emailEditText = (EditText) findViewById(R.id.enterEmail);
        passwordEditText = (EditText) findViewById(R.id.enterPassword);
    }

    /**
     * Checks whether the entered username and password are in the system
     * @param view The view for loggin in
     */
    public void loginApp(View view) {
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();


        if (email.equals("")) {
            Toast.makeText(LoginActivity.this, "Enter Email Address!",
                    Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(LoginActivity.this, "Enter Password!",
                    Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Authentication failed",
                                        Toast.LENGTH_SHORT).show();
                            } else if (!mAuth.getCurrentUser().isEmailVerified()){
                                Toast.makeText(LoginActivity.this,
                                        "Please verify your Email Address!",
                                        Toast.LENGTH_SHORT).show();
                                mAuth.getCurrentUser().sendEmailVerification();
                            } else {
                                FirebaseDatabase.getInstance().getReference(References.USER_TABLE)
                                        .child(mAuth.getCurrentUser().getUid())
                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                String userType = dataSnapshot.child("accountType")
                                                        .getValue(String.class);
                                                if (userType.equals(UserType.MANAGER.name())) {
                                                    References.setCurrentUser(dataSnapshot
                                                            .getValue(Manager.class));
                                                } else if (userType.equals(UserType.WORKER.name())) {
                                                    References.setCurrentUser(dataSnapshot
                                                            .getValue(Worker.class));
                                                } else if (userType.equals(UserType.ADMIN.name())) {
                                                    References.setCurrentUser(dataSnapshot
                                                            .getValue(Admin.class));
                                                } else {
                                                    References.setCurrentUser(dataSnapshot
                                                            .getValue(GeneralUser.class));
                                                }
                                                Log.i("Current User", References.getCurrentUser()
                                                        .getAccountType());
                                                Intent intent = new Intent(LoginActivity.this,
                                                        LoadSourceReportActivity.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                Log.e("Login", databaseError.getMessage());
                                                Toast.makeText(LoginActivity.this,
                                                        "An error occured. Please try again!",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });

                            }

                        }
                    });
        }
    }

    /**
     * Retruns to the previous screen if user presses cancel
     * @param view The view for this method
     */
    public void cancel(View view) {
        LoginActivity.this.finish();
    }

}
