package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class sup_alot extends AppCompatActivity {
    int r, j;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sup_alot);
        Intent in = getIntent();
        final String tex = in.getStringExtra(alt.EXTRA_NUMBER); //DEVICE No

        final EditText sun = (EditText) findViewById(R.id.supn);
        TextView did = (TextView) findViewById(R.id.deid);
        did.setText(tex);
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("location");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String s = dataSnapshot.child(tex).child("supervisor").getValue().toString();
                sun.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button sa = (Button) findViewById(R.id.savee);
        sa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String p = sun.getText().toString();
            addf(p);
                Toast.makeText(getApplicationContext(),"Supervisor changed sucessfully",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addf(String p) {
        TextView t = (TextView) findViewById(R.id.deid);
        String z = t.getText().toString();
        final DatabaseReference tr;
        tr = FirebaseDatabase.getInstance().getReference().child("location");
        tr.child(z).child("supervisor").setValue(p);
    }
}

