package com.example.anmol.thirstquencher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void cancel(View view) {
        LoginActivity.this.finish();
    }

    public void loginApp(View view) {
        String username = ((EditText) findViewById(R.id.enterUsername)).getText().toString();
        String password = ((EditText) findViewById(R.id.enterPassword)).getText().toString();

        if (MainActivity.userpasswords.containsKey(username) && password.equals(MainActivity.userpasswords.get(username))) {
            Intent intent = new Intent(LoginActivity.this, HomeScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            CharSequence text = "Incorrect Credentials!";
            Toast wrongPassword = Toast.makeText(LoginActivity.this.getApplicationContext(), text, Toast.LENGTH_LONG);
            wrongPassword.show();
        }
    }
}
