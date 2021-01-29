package com.example.baiscac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class guide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        if(ActivityCompat.checkSelfPermission(guide.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(guide.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(guide.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
            ActivityCompat.requestPermissions(guide.this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},44);
        }
        if(ActivityCompat.checkSelfPermission(guide.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(guide.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},44);
        }
        Button st = (Button) findViewById(R.id.adf);
        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ope();
            }
        });
    }

    public void ope() {
        Intent home;
        home = new Intent(this, gender.class);
               startActivity(home);
    }
}
