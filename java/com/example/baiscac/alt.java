package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class alt extends AppCompatActivity {
    int r;
    int glo;
    int i,j;
    String[] sup = new String[999];
    String[] supp = new String[999];

    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt);


        final DatabaseReference abh;
        abh = FirebaseDatabase.getInstance().getReference().child("location");

        abh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                r = (int) dataSnapshot.getChildrenCount();
                glo = r;
                for (i=1;i<=r;i++){
                    sup[i] = dataSnapshot.child(String.valueOf(i)).child("supervisor").getValue().toString();
                    supp[i] = sup[i];
                    addd(i,sup[i]);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button al = (Button) findViewById(R.id.alo);
        al.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opena();
            }
        });



        Button c = (Button) findViewById(R.id.Clean);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });




    }

    public void opena() {
       EditText tii = (EditText) findViewById(R.id.toi);
       String datt = tii.getText().toString();


        Intent ntent = new Intent(this, sup_alot.class);
       ntent.putExtra(EXTRA_NUMBER, datt);
        startActivity(ntent);
    }

    public void addd(int z, String x) {
        String d = String.valueOf(z);
        TableLayout tl = (TableLayout) findViewById(R.id.allotment_list);
        TableRow tr1 = new TableRow(this);
        TextView trr = new TextView(this);
        //d = "   " + d;
        trr.setText(d);
        trr.setTextSize(25);
        trr.setGravity(Gravity.CENTER);
        TextView trrr = new TextView(this);
        //x = "" + x;
        trrr.setText(x);
        trrr.setTextSize(25);
        trrr.setGravity(Gravity.CENTER);
        trrr.setId(z);
        tr1.addView(trr);
        tr1.addView(trrr);
        tl.addView(tr1);

    }

    public void open() {
        EditText ti = (EditText) findViewById(R.id.toi);
        String dat = ti.getText().toString();


        Intent intent = new Intent(this, cleanmap.class);
        intent.putExtra(EXTRA_TEXT, dat);
        startActivity(intent);
    }

}
