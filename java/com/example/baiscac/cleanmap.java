package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class cleanmap extends AppCompatActivity {
    String[] n = new String[999];
    String[] d = new String[999];
    String w,c,m,se,hy,tp,si;
    String[] r = new String[999];
    String[] z = new String[999];
    int i=0;
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleanmap);
        final TextView tn1 = (TextView) findViewById(R.id.cn1);
        final TextView tn2 = (TextView) findViewById(R.id.cn2);
        final TextView tn3 = (TextView) findViewById(R.id.cn3);
        final TextView tn4 = (TextView) findViewById(R.id.cn4);
        final TextView tn5 = (TextView) findViewById(R.id.cn5);

        final TextView ti1 = (TextView) findViewById(R.id.ci1);
        final TextView ti2 = (TextView) findViewById(R.id.ci2);
        final TextView ti3 = (TextView) findViewById(R.id.ci3);
        final TextView ti4 = (TextView) findViewById(R.id.ci4);
        final TextView ti5 = (TextView) findViewById(R.id.ci5);
        final TextView wa = (TextView) findViewById(R.id.water);
        final TextView con = (TextView) findViewById(R.id.condit);
        final TextView humi = (TextView) findViewById(R.id.hu);
        final TextView tep = (TextView) findViewById(R.id.tem);
        final TextView hs = (TextView) findViewById(R.id.hs);
        //final TextView ca = (TextView) findViewById(R.id.co);
        final TextView me = (TextView) findViewById(R.id.meth);
        final TextView ca = (TextView) findViewById(R.id.co);
        final TextView re1 = (TextView) findViewById(R.id.r1);
        final TextView re2 = (TextView) findViewById(R.id.r2);
        final TextView re3 = (TextView) findViewById(R.id.r3);
        final TextView re4 = (TextView) findViewById(R.id.r4);
        final TextView re5 = (TextView) findViewById(R.id.r5);

        Intent intent   =   getIntent();
        final String text = intent.getStringExtra(MapsActivity.EXTRA_TEXT);
        TextView h = (TextView) findViewById(R.id.devid);
        h.setText(text);
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("location").child(text);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                d[0] =  dataSnapshot.child("cleaners").child("1").child("ID").getValue().toString();
                d[1] =  dataSnapshot.child("cleaners").child("2").child("ID").getValue().toString();
                d[2] =  dataSnapshot.child("cleaners").child("3").child("ID").getValue().toString();
                d[3] =  dataSnapshot.child("cleaners").child("4").child("ID").getValue().toString();
                d[4] =  dataSnapshot.child("cleaners").child("5").child("ID").getValue().toString();

                final DatabaseReference refp;
                refp = FirebaseDatabase.getInstance().getReference().child("cleaner");
                refp.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        z[0] = dataSnapshot.child(d[0]).child("USER").child("ID").getValue().toString();
                        z[1] = dataSnapshot.child(d[1]).child("USER").child("ID").getValue().toString();
                        z[2] = dataSnapshot.child(d[2]).child("USER").child("ID").getValue().toString();
                        z[3] = dataSnapshot.child(d[3]).child("USER").child("ID").getValue().toString();
                        z[4] = dataSnapshot.child(d[4]).child("USER").child("ID").getValue().toString();
                        ti1.setText(z[0]);
                        ti2.setText(z[1]);
                        ti3.setText(z[2]);
                        ti4.setText(z[3]);
                        ti5.setText(z[4]);

                        n[0] = dataSnapshot.child(d[0]).child("USER").child("NAME").getValue().toString();
                        n[1] = dataSnapshot.child(d[1]).child("USER").child("NAME").getValue().toString();
                        n[2] = dataSnapshot.child(d[2]).child("USER").child("NAME").getValue().toString();
                        n[3] = dataSnapshot.child(d[3]).child("USER").child("NAME").getValue().toString();
                        n[4] = dataSnapshot.child(d[4]).child("USER").child("NAME").getValue().toString();

                        tn1.setText(n[0]);
                        tn2.setText(n[1]);
                        tn3.setText(n[2]);
                        tn4.setText(n[3]);
                        tn5.setText(n[4]);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





                w = dataSnapshot.child("water").getValue().toString();
                c = dataSnapshot.child("condition").getValue().toString();
                wa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Water");
                    }
                });
                wa.setText(w);
                if(w.equals("low")){
                    wa.setTextColor(Color.RED);
                }
                else if(w.equals("sufficient")){
                    wa.setTextColor(Color.GREEN);
                }
                con.setText(c);
                if(c.equals("usable")){
                    con.setTextColor(Color.GREEN);
                }
                else{
                    con.setTextColor(Color.RED);
                }
                se = dataSnapshot.child("parameters").child("ammonia").getValue().toString();
                m = dataSnapshot.child("parameters").child("methane").getValue().toString();
                hy = dataSnapshot.child("parameters").child("Humidity").getValue().toString();
                tp = dataSnapshot.child("parameters").child("Temperature").getValue().toString();
                si = dataSnapshot.child("visitors").getValue().toString();
                me.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Methane");
                    }
                });
                me.setText(m+" ppm");
                if(Float.parseFloat(m)>500){
                    me.setTextColor(Color.RED);
                }
                else {
                    me.setTextColor(Color.GREEN);
                }
                ca.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Ammonia");
                    }
                });
                ca.setText(se+" ppm");
                if(Float.parseFloat(se)>20){
                    ca.setTextColor(Color.RED);
                }
                else {
                    ca.setTextColor(Color.GREEN);
                }
                humi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Humidity");
                    }
                });
                humi.setText(hy);
                if(Float.parseFloat(hy)>100){
                    humi.setTextColor(Color.RED);
                }
                else {
                    humi.setTextColor(Color.GREEN);
                }
                hs.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Visitors");
                    }
                });
                hs.setText(si);
                tep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        opgr("Temperature");
                    }
                });
                tep.setText(tp+" Clecius");
                if(Float.parseFloat(tp)>40){
                    tep.setTextColor(Color.RED);
                }
                else {
                    tep.setTextColor(Color.BLUE);
                }


                r[0] =  dataSnapshot.child("reviews").child("1").getValue().toString();
                r[1] =  dataSnapshot.child("reviews").child("2").getValue().toString();
                r[2] =  dataSnapshot.child("reviews").child("3").getValue().toString();
                r[3] =  dataSnapshot.child("reviews").child("4").getValue().toString();
                r[4] =  dataSnapshot.child("reviews").child("5").getValue().toString();

                re1.setText(r[0]);
                re2.setText(r[1]);
                re3.setText(r[2]);
                re4.setText(r[3]);
                re5.setText(r[4]);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void opgr(String j) {
        Intent intents = new Intent(this, graph.class);
        intents.putExtra(EXTRA_TEXT,j);
        startActivity(intents);
    }
}
