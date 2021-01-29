package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class imagesd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagesd);
        final ImageView image1 = (ImageView) findViewById(R.id.i1);
        final ImageView image2 = (ImageView) findViewById(R.id.i2);
        final ImageView image3 = (ImageView) findViewById(R.id.i3);
        Intent io = getIntent();
        final String no = io.getStringExtra(display_box.EXTRA_TEXT);
        int count = getcount();

        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("location");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ur1 = dataSnapshot.child(no).child("climg").child("3").getValue().toString();
                Picasso.get().load(ur1).into(image1); // images
                String ur2 = dataSnapshot.child(no).child("climg").child("2").getValue().toString();
                Picasso.get().load(ur2).into(image2); // images
                String ur3 = dataSnapshot.child(no).child("climg").child("1").getValue().toString();
                Picasso.get().load(ur3).into(image3); // images
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public int getcount() {
        final int[] p = new int[1];
        final DatabaseReference re;
        re = FirebaseDatabase.getInstance().getReference().child("location").child("3").child("climg");
        re.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                p[0] = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return p[0];
    }
}
