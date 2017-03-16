package com.example.anmol.thirstquencher.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anmol.thirstquencher.R;

/**
 * The controller for logging in
 * @author Anmol
 * @version 2/20/17
 */
public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.enterUsername);
        passwordEditText = (EditText) findViewById(R.id.enterPassword);
    }

    /**
     * Retruns to the previous screen if user presses cancel
     * @param view The view for this method
     */
    public void cancel(View view) {
        LoginActivity.this.finish();
    }

    /**
     * Checks whether the entered username and password are in the system
     * @param view The view for loggin in
     */
    public void loginApp(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (MainActivity.userAccounts.containsKey(username) && password.equals(MainActivity.userAccounts.get(username).getPassword())) {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("USERNAME", username);
            startActivity(intent);
        } else {
            CharSequence text = "Incorrect Credentials!";
            Toast wrongPassword = Toast.makeText(LoginActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            wrongPassword.show();
        }
    }
}
