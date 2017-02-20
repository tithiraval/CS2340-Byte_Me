package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void register(View view) {
        String username = ((EditText) findViewById(R.id.registerUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.registerPassword)).getText().toString();

        if (username.equals("")) {
            CharSequence text = "Enter Username!";
            Toast emptyUsername = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyUsername.show();
        } else if (MainActivity.userpasswords.containsKey(username)) {
            CharSequence text = "Username Already Taken!";
            Toast alreadyTaken = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            alreadyTaken.show();
        } else if (password.equals("")) {
            CharSequence text = "Enter Password!";
            Toast emptyPassword = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyPassword.show();
        } else {
            MainActivity.userpasswords.put(username, password);
            CharSequence text = "Account Created";
            Toast accountCreated = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            accountCreated.show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void cancelRegistration(View view) {
        RegistrationActivity.this.finish();
    }
}
