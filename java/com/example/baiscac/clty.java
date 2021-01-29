package com.example.baiscac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class clty extends AppCompatActivity {
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clty);
        Intent iny = getIntent();
        String o = iny.getStringExtra(display_box.EXTRA_TEXT);
        TextView uu = (TextView) findViewById(R.id.cb);
        uu.setText("Cleaner  allotment window  "+o);
    }
}
