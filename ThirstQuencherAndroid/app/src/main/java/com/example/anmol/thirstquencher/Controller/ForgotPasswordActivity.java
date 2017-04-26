package com.example.anmol.thirstquencher.Controller;

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

import com.example.anmol.thirstquencher.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Animation animAlpha;
    private EditText emailInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        emailInput = (EditText) findViewById(R.id.sendEmailEditText);
    }

    public void sendEmail(View view) {
        Button sendEmail = (Button) view;
        sendEmail.startAnimation(animAlpha);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = emailInput.getText().toString();

        if (emailAddress.length() == 0 || !emailAddress.contains("@")
                || !emailAddress.contains(".")) {
            Toast.makeText(ForgotPasswordActivity.this, "Enter valid email address",
                    Toast.LENGTH_SHORT).show();
        } else {
            auth.sendPasswordResetEmail(emailAddress)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("ForgotPassword", "Email sent.");
                                Toast.makeText(ForgotPasswordActivity.this, "Email Sent!",
                                        Toast.LENGTH_SHORT).show();
                                ForgotPasswordActivity.this.finish();
                            } else {
                                if (task.getException()
                                        instanceof FirebaseAuthInvalidUserException) {
                                    Toast.makeText(ForgotPasswordActivity.this,
                                            "Email Address is not registered",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("ForgotPassword", task.getException().getMessage());
                                    Toast.makeText(ForgotPasswordActivity.this,
                                            "An error occurred! Please try again",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
    }

    /**
     * Returns back to the previous screen if the user presses cancel.
     * @param view The view for change password screen
     */
    public void cancelChangePassword(View view) {
        Button cancel = (Button) findViewById(R.id.forgotPasswordCancelButton);
        cancel.startAnimation(animAlpha);
        ForgotPasswordActivity.this.finish();
    }
}
