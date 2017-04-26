package com.example.anmol.thirstquencher.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import com.example.anmol.thirstquencher.R;
import android.os.Handler;

/**
 * Controller for the splash screen
 * @author Tithi
 * @version 4/25/17
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
