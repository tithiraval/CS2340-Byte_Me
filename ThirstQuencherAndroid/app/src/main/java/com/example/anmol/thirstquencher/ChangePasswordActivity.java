package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChangePasswordActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
    }

    public void cancelChangePassword(View view) {
        ChangePasswordActivity.this.finish();
    }
}
