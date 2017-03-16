package com.example.anmol.thirstquencher.Controller;

import com.example.anmol.thirstquencher.Model.SourceReport;
import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

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

    private CallbackManager callbackManager;
    public static HashMap<String, User> userAccounts = new HashMap<String, User>();
    public static List<SourceReport> waterReports = new ArrayList<SourceReport>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
                CharSequence text = "Facebook Login Cancelled!";
                Toast cancelled = Toast.makeText(MainActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
                cancelled.show();
            }

            @Override
            public void onError(FacebookException exception) {
                CharSequence text = "Facebook Login Error!";
                Toast error = Toast.makeText(MainActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
                error.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

    /**
     * Prompts login screen for facebook when called
     * @param view The view for logging into facebook
     */
    public void facebookLogin(View view) {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
    }

    /**
     * Checks whether the user is logged in
     * @return True if the user is logged in; false if the user is not
     */
    public static boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }
}
