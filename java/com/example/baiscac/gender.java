package com.example.baiscac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class gender extends AppCompatActivity {
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        ImageView male = (ImageView) findViewById(R.id.ma);
        ImageView female = (ImageView) findViewById(R.id.fe);
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmaps("male");
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openmaps("female");
            }
        });
        Button admin_login = (Button) findViewById(R.id.adm);
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadmin();
            }
        });
    }

    public void openadmin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void openmaps(String gend) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(EXTRA_TEXT, gend);
        intent.putExtra(EXTRA_NUMBER, "12");
        startActivity(intent);

    }
}
