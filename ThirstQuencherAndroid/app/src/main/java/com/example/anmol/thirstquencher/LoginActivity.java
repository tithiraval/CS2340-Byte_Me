package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    public void cancel(View view) {
        LoginActivity.this.finish();
    }

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
