package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.Model.User;
import com.example.anmol.thirstquencher.R;

/**
 * The class for changing a user's password
 * @author Anmol
 * @version 2/20/17
 */
public class ChangePasswordActivity extends AppCompatActivity {

    private User user;
    private EditText currentPassword;
    private EditText newPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        user = MainActivity.userAccounts.get(getIntent().getStringExtra("USERNAME"));
        currentPassword = (EditText) findViewById(R.id.changePasswordEnterCurrent);
        newPassword = (EditText) findViewById(R.id.changePasswordEnterNew);
    }

    /**
     * Changes the user's password if matches restrictions
     * @param view The view for this screen
     */
    public void confirmChangePassword(View view) {

        String currentPasswordText = currentPassword.getText().toString();
        String newPasswordText = newPassword.getText().toString();

        if (!currentPasswordText.equals(user.getPassword())) {
            CharSequence text = "Incorrect Current Password!";
            Toast incorrectPassword = Toast.makeText(ChangePasswordActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            incorrectPassword.show();
        } else if (newPasswordText.equals("")) {
            CharSequence text = "New Password cannot be empty!";
            Toast emptyPassword = Toast.makeText(ChangePasswordActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyPassword.show();
        } else {
            user.setPassword(newPasswordText);
            CharSequence text = "Password Successfully Changed";
            Toast passwordChanged = Toast.makeText(ChangePasswordActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            passwordChanged.show();
            ChangePasswordActivity.this.finish();
        }
    }

    /**
     * Returns back to the previous screen if the user presses cancel.
     * @param view The view for change password screen
     */
    public void cancelChangePassword(View view) {
        ChangePasswordActivity.this.finish();
    }
}
