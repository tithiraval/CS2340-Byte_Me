package com.example.anmol.thirstquencher.controller;

import com.example.anmol.thirstquencher.model.QualityReport;
import com.example.anmol.thirstquencher.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for the app
 * @author Anmol
 * @version 2/20/17
 */
public class MainActivity extends AppCompatActivity {

    public static List<QualityReport> qualityReports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method for logging into the app
     */
    public void login(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Initiates registration when called
     */
    public void startRegistration(@SuppressWarnings("UnusedParameters") View view) {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

}
