package com.example.birthdayreminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import io.opencensus.trace.export.SpanExporter;

public class SplashActivity extends AppCompatActivity {
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("Password","");




        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if password equals null
                if(password.equals("")){
                    Intent intent = new Intent(getApplicationContext(), PasswordCreate.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(), EnterPassword.class);
                    startActivity(intent);
                    finish();
                }



            }
        },2000);
    }
}
