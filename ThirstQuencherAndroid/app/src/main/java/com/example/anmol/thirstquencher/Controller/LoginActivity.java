package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.Admin;
import com.example.anmol.thirstquencher.Model.GeneralUser;
import com.example.anmol.thirstquencher.Model.Manager;
import com.example.anmol.thirstquencher.Model.References;
import com.example.anmol.thirstquencher.Model.User;
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
 */
public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Animation animAlpha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        emailEditText = (EditText) findViewById(R.id.enterEmail);
        passwordEditText = (EditText) findViewById(R.id.enterPassword);
    }

    /**
     * Checks whether the entered username and password are in the system
     * @param view The view for loggin in
     */
    public void loginApp(View view) {
        Button login = (Button) findViewById(R.id.loginButton);
        login.startAnimation(animAlpha);
        final String email = emailEditText.getText().toString();
        final String password = passwordEditText.getText().toString();


        if (email.equals("")) {
            Toast.makeText(LoginActivity.this, "Enter Email Address!",
                    Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(LoginActivity.this, "Password should be at least 6 characters!",
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
                                                if (!dataSnapshot.child("password").getValue(
                                                        String.class).equals(password)) {
                                                    FirebaseDatabase.getInstance()
                                                            .getReference(References.USER_TABLE)
                                                            .child(mAuth.getCurrentUser().getUid())
                                                            .child("password").setValue(password);
                                                    References.getCurrentUser().setPassword(password);
                                                }
                                                Log.i("Current User", References.getCurrentUser()
                                                        .getAccountType());
                                                Intent intent = new Intent(LoginActivity.this,
                                                        HomeScreenActivity.class);
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
     * Returns to the previous screen if user presses cancel
     * @param view The view for this method
     */
    public void cancel(View view) {
        Button cancel = (Button) findViewById(R.id.cancelButton);
        cancel.startAnimation(animAlpha);
        LoginActivity.this.finish();
        mAuth.signOut();
        References.setCurrentUser((User) null);
    }

    /**
     * Goes to the forgot password screen
     * @param view The view for this screen
     */
    public void forgotPassword(View view) {
        Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }
}
