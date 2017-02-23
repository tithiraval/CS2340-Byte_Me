package com.example.anmol.thirstquencher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * The class for changing a user's password
 * @author Byte_Me (Team 10)
 * @version 2/20/17
 */
public class ChangePasswordActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
    }

    /**
     * Returns back to the previous screen if the user presses cancel.
     * @param view The view for change password screen
     */
    public void cancelChangePassword(View view) {
        ChangePasswordActivity.this.finish();
    }
}
