package com.example.anmol.thirstquencher.Controller;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Controller for the first functional screen of the app
 * @author Anmol, Tithi
 * @version 4/25/17
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Method for logging into the app
     * @param view The button to login
     */
    public void login(View view) {
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        Button loginButton = (Button) findViewById(R.id.login);
        loginButton.startAnimation(animAlpha);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Initiates registration when called
     * @param view The button to register
     */
    public void startRegistration(View view) {
        Button registerButton = (Button) findViewById(R.id.register);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        registerButton.startAnimation(animAlpha);
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

}
