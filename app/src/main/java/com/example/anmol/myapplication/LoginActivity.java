package com.example.anmol.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    int status = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cancel(View view) {
        this.finish();
    }

    public void loginApp(View view) {
        EditText username = (EditText) findViewById(R.id.enterUsername);
        EditText password = (EditText) findViewById(R.id.enterPassword);

        if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")) {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivty.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            CharSequence text = "Incorrect Credentials!";
            Toast wrongPassword = Toast.makeText(LoginActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            wrongPassword.show();
        }
    }
}
