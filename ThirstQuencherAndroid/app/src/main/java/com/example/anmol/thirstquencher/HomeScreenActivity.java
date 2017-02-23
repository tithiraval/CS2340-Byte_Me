package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.LoginManager;

public class HomeScreenActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
    }

    public void viewProfile(View view) {
        Intent intent = new Intent(HomeScreenActivity.this, ViewProfileActivity.class);
        intent.putExtra("USERNAME", user.getUsername());
        startActivity(intent);
    }

    public void logOut(View view) {
        if (MainActivity.isLoggedIn()) {
            LoginManager.getInstance().logOut();
        }
        Intent intent = new Intent(HomeScreenActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
