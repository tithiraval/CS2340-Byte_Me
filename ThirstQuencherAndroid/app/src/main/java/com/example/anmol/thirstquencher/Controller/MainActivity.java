package com.example.anmol.thirstquencher.Controller;

import com.example.anmol.thirstquencher.Model.QualityReport;
import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
     * @param view The view for logging in
     */
    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * Initiates registration when called
     * @param view The view for registering
     */
    public void startRegistration(View view) {
        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

}
