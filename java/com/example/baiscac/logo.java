package com.example.baiscac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class logo extends AppCompatActivity {
    private static int SPLASH_TIME= 1200;
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String userr = "user";

                Intent home;
                home = new Intent(logo.this, guide.class);
                home.putExtra(EXTRA_TEXT, userr);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME);
    }
}
