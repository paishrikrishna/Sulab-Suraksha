package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class graph extends AppCompatActivity {
    LineChart lc;
    float a,b,c,d,e,f,g;

    String textg,tr ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent in1 = getIntent();
        textg = in1.getStringExtra(display_box.EXTRA_TEXT);
        tr = in1.getStringExtra(cleanmap.EXTRA_TEXT);
        lc = (LineChart) findViewById(R.id.bgp);
        if(!tr.isEmpty()){
            textg = tr;
        }
        else if(!textg.isEmpty()){
            textg = textg;
        }


        LineDataSet lds1 = new LineDataSet(data1(),textg);
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
        ArrayList<Entry> dv = new ArrayList<Entry>();
        if(textg.equals("Methane")) {
            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te+"(in PPM)");
            dv.add(new Entry(1, 320));
            dv.add(new Entry(2, 320));
            dv.add(new Entry(3,320));
            dv.add(new Entry(4, 321));
            dv.add(new Entry(5, 322));
            dv.add(new Entry(6, 322));
            dv.add(new Entry(7, 321));
            dv.add(new Entry(8, 430));
            dv.add(new Entry(9, 440));
            dv.add(new Entry(10, 550));
            dv.add(new Entry(11, 321));
            dv.add(new Entry(12, 322));
            dv.add(new Entry(13, 327));
            dv.add(new Entry(14, 344));
            dv.add(new Entry(15, 353));
            dv.add(new Entry(16, 353));
            dv.add(new Entry(17, 322));
            dv.add(new Entry(18, 322));
            dv.add(new Entry(19, 332));
            dv.add(new Entry(20, 332));
            dv.add(new Entry(21, 332));
            dv.add(new Entry(22, 334));
            dv.add(new Entry(23, 335));
            dv.add(new Entry(24, 333));
        }
        else if(textg.equals("Visitors")){

            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te);
            dv.add(new Entry(1, 0));
            dv.add(new Entry(2, 0));
            dv.add(new Entry(3, 0));
            dv.add(new Entry(4, 0));
            dv.add(new Entry(5, 0));
            dv.add(new Entry(6, 0));
            dv.add(new Entry(7, 0));
            dv.add(new Entry(8, 2));
            dv.add(new Entry(9, 3));
            dv.add(new Entry(10, 2));
            dv.add(new Entry(11, 3));
            dv.add(new Entry(12, 5));
            dv.add(new Entry(13, 4));
            dv.add(new Entry(14, 0));
            dv.add(new Entry(15, 1));
            dv.add(new Entry(16, 5));
            dv.add(new Entry(17, 8));
            dv.add(new Entry(18, 3));
            dv.add(new Entry(19, 1));
            dv.add(new Entry(20, 0));
            dv.add(new Entry(21, 2));
            dv.add(new Entry(22, 0));
            dv.add(new Entry(23, 0));
            dv.add(new Entry(24, 0));
        }
        else if(textg.equals("Ammonia")){

            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te+"(in PPM)");
            dv.add(new Entry(1, 20));
            dv.add(new Entry(2, 20));
            dv.add(new Entry(3, 20));
            dv.add(new Entry(4, 21));
            dv.add(new Entry(5, 22));
            dv.add(new Entry(6, 22));
            dv.add(new Entry(7, 21));
            dv.add(new Entry(8, 23));
            dv.add(new Entry(9, 24));
            dv.add(new Entry(10, 25));
            dv.add(new Entry(11, 26));
            dv.add(new Entry(12, 27));
            dv.add(new Entry(13, 20));
            dv.add(new Entry(14, 24));
            dv.add(new Entry(15, 23));
            dv.add(new Entry(16, 23));
            dv.add(new Entry(17, 28));
            dv.add(new Entry(18, 29));
            dv.add(new Entry(19, 22));
            dv.add(new Entry(20, 22));
            dv.add(new Entry(21, 20));
            dv.add(new Entry(22, 20));
            dv.add(new Entry(23, 21));
            dv.add(new Entry(24, 21));
        }
        else if(textg.equals("Humidity")){

            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te);
            dv.add(new Entry(1, 50));
            dv.add(new Entry(2, 50));
            dv.add(new Entry(3, 50));
            dv.add(new Entry(4, 51));
            dv.add(new Entry(5, 52));
            dv.add(new Entry(6, 52));
            dv.add(new Entry(7, 51));
            dv.add(new Entry(8, 53));
            dv.add(new Entry(9, 54));
            dv.add(new Entry(10, 55));
            dv.add(new Entry(11, 56));
            dv.add(new Entry(12, 57));
            dv.add(new Entry(13, 50));
            dv.add(new Entry(14, 54));
            dv.add(new Entry(15, 53));
            dv.add(new Entry(16, 63));
            dv.add(new Entry(17, 58));
            dv.add(new Entry(18, 49));
            dv.add(new Entry(19, 52));
            dv.add(new Entry(20, 52));
            dv.add(new Entry(21, 50));
            dv.add(new Entry(22, 50));
            dv.add(new Entry(23, 51));
            dv.add(new Entry(24, 51));
        }
        else if(textg.equals("Temperature")){

            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te+"(in Celcius)");
            dv.add(new Entry(1, 28));
            dv.add(new Entry(2, 29));
            dv.add(new Entry(3, 29));
            dv.add(new Entry(4, 28));
            dv.add(new Entry(5, 28));
            dv.add(new Entry(6, 28));
            dv.add(new Entry(7, 28));
            dv.add(new Entry(8, 28));
            dv.add(new Entry(9, 28));
            dv.add(new Entry(10, 28));
            dv.add(new Entry(11, 29));
            dv.add(new Entry(12, 32));
            dv.add(new Entry(13, 33));
            dv.add(new Entry(14, 34));
            dv.add(new Entry(15, 34));
            dv.add(new Entry(16, 33));
            dv.add(new Entry(17, 28));
            dv.add(new Entry(18, 29));
            dv.add(new Entry(19, 29));
            dv.add(new Entry(20, 28));
            dv.add(new Entry(21, 27));
            dv.add(new Entry(22, 27));
            dv.add(new Entry(23, 27));
            dv.add(new Entry(24, 27));
        }
        else if(textg.equals("Water")){

            TextView lab = (TextView) findViewById(R.id.textView50);
            String te = "X-Axis : Time (in Hrs)"+"Y:axis Values";
            lab.setText(te+"(in Liters)");
            dv.add(new Entry(1, 8));
            dv.add(new Entry(2, 8));
            dv.add(new Entry(3, 8));
            dv.add(new Entry(4, 8));
            dv.add(new Entry(5, 8));
            dv.add(new Entry(6, 8));
            dv.add(new Entry(7, 8));
            dv.add(new Entry(8, 8));
            dv.add(new Entry(9, 7));
            dv.add(new Entry(10, 7));
            dv.add(new Entry(11, 7));
            dv.add(new Entry(12, 6));
            dv.add(new Entry(13, 6));
            dv.add(new Entry(14, 5));
            dv.add(new Entry(15, 8));
            dv.add(new Entry(16, 8));
            dv.add(new Entry(17, 7));
            dv.add(new Entry(18, 7));
            dv.add(new Entry(19, 8));
            dv.add(new Entry(20, 8));
            dv.add(new Entry(21, 8));
            dv.add(new Entry(22, 8));
            dv.add(new Entry(23, 8));
            dv.add(new Entry(24, 8));
        }
        else{
            dv.add(new Entry(1, 35));
            dv.add(new Entry(2, 16));
            dv.add(new Entry(3, 8));
            dv.add(new Entry(4, 5));
            dv.add(new Entry(5, 42));
            dv.add(new Entry(6, 12));
            dv.add(new Entry(7, 25));
        }
        return dv;
    }


}
