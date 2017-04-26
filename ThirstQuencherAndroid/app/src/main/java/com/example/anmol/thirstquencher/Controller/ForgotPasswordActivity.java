package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.anmol.thirstquencher.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Animation animAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
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
