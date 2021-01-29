package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class secgraph extends AppCompatActivity {
    LineChart lc;
    float a,b,c,d,e,f,g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secgraph);
        lc = (LineChart) findViewById(R.id.para);
        LineDataSet lds1 = new LineDataSet(data1(),"Water");
       // LineDataSet lds2 = new LineDataSet(data2(),"Methane");
        lds1.setColor(Color.RED);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lds1);
        //dataSets.add(lds2);
        LineData data = new LineData(dataSets);
        lc.setData(data);
        lc.invalidate();

    }
    private ArrayList<Entry>data1(){


        Intent in1 = getIntent();
        final String textg = in1.getStringExtra(display_box.EXTRA_TEXT);
        final DatabaseReference tr;
        tr = FirebaseDatabase.getInstance().getReference().child("location").child("1").child("avg").child("ammonia");
        tr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a = Float.parseFloat(dataSnapshot.child("1").getValue().toString());
                b =  Float.parseFloat(dataSnapshot.child("2").getValue().toString());
                c = Float.parseFloat(dataSnapshot.child("3").getValue().toString());
                d = Float.parseFloat(dataSnapshot.child("4").getValue().toString());
                e = Float.parseFloat(dataSnapshot.child("5").getValue().toString());
                f = Float.parseFloat(dataSnapshot.child("6").getValue().toString());
                g = Float.parseFloat(dataSnapshot.child("7").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayList<Entry> dv = new ArrayList<Entry>();
        dv.add(new Entry(1, 5));
        dv.add(new Entry(2,6));
        dv.add(new Entry(3,8));
        dv.add(new Entry(4,15));
        dv.add(new Entry(5,2));
        dv.add(new Entry(6,2));
        dv.add(new Entry(7,5));
        return dv;
    }


}