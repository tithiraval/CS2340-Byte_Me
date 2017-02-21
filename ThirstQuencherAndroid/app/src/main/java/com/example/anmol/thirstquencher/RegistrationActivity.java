package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Spinner userTypeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = (EditText) findViewById(R.id.registerUsername);
        passwordEditText = (EditText) findViewById(R.id.registerPassword);
        userTypeSpinner = (Spinner) findViewById(R.id.registerSpinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, User.legalUserTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);
    }

    public void register(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.equals("")) {
            CharSequence text = "Enter Username!";
            Toast emptyUsername = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyUsername.show();
        } else if (MainActivity.userAccounts.containsKey(username)) {
            CharSequence text = "Username Already Taken!";
            Toast alreadyTaken = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            alreadyTaken.show();
        } else if (password.equals("")) {
            CharSequence text = "Enter Password!";
            Toast emptyPassword = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            emptyPassword.show();
        } else {
            this.createUser(username, password, (String) userTypeSpinner.getSelectedItem());
            CharSequence text = "Account Created";
            Toast accountCreated = Toast.makeText(RegistrationActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            accountCreated.show();
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void createUser(String username, String password, String typeOfUser) {
        if (typeOfUser.equals("Manager")) {
            MainActivity.userAccounts.put(username, new Manager(username, password));
        } else if (typeOfUser.equals("Worker")) {
            MainActivity.userAccounts.put(username, new Worker(username, password));
        } else if (typeOfUser.equals("Admin")) {
            MainActivity.userAccounts.put(username, new Admin(username, password));
        } else {
            MainActivity.userAccounts.put(username, new GeneralUser(username, password));
        }
    }

    public void cancelRegistration(View view) {
        RegistrationActivity.this.finish();
    }
}
