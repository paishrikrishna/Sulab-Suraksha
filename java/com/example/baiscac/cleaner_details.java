package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class cleaner_details extends AppCompatActivity {
    private StorageReference Folder;

    private static  final int ImageBack=1;
    int r;
    int glo;
    int i,j;
    String[] sup = new String[9999];
    String[] supp = new String[9999];
    String a,b,c,d,e,f,g,h,m,cc;
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaner_details);
        TextView t1 = (TextView) findViewById(R.id.d1);
        t1.setText("2");
        TextView t2 = (TextView) findViewById(R.id.c1);
        t2.setText("unusable");
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        Intent intent   =   getIntent();
        final String texts = intent.getStringExtra(cleaner_details.EXTRA_TEXT);
        if(!texts.isEmpty()){
            cc = texts;
        }
        else {
            final String text = intent.getStringExtra(MapsActivity.EXTRA_TEXT);
            cc = text;
        }
        Button refd = (Button) findViewById(R.id.rel);
        refd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg();
            }
        });
        final DatabaseReference ref;
        ref = FirebaseDatabase.getInstance().getReference().child("cleaner").child(cc);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a = dataSnapshot.child("USER").child("NAME").getValue().toString();
                b = dataSnapshot.child("USER").child("ID").getValue().toString();
                c = dataSnapshot.child("RECORD").child("t1").getValue().toString();
                d = dataSnapshot.child("RECORD").child("t2").getValue().toString();
                TextView c_n = (TextView) findViewById(R.id.cln);
                c_n.setText("  "+a);
                TextView c_id = (TextView) findViewById(R.id.clid);
                c_id.setText("  "+b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final  DatabaseReference t;
        t = FirebaseDatabase.getInstance().getReference().child("location");
        t.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                e = dataSnapshot.child(c).child("device").getValue().toString();
                f = dataSnapshot.child(c).child("condition").getValue().toString();
                //g = dataSnapshot.child(d).child("device").getValue().toString();
                //h = dataSnapshot.child(d).child("condition").getValue().toString();
               // TextView t1 = (TextView) findViewById(R.id.d1);
                //t1.setText("3");
                //TextView t2 = (TextView) findViewById(R.id.c1);
                //t2.setText("unusable");
                //TextView t3 = (TextView) findViewById(R.id.d2);
                //t3.setText(g);
                //TextView t4 = (TextView) findViewById(R.id.c2);
                //t4.setText(h);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference abh;
        abh = FirebaseDatabase.getInstance().getReference().child("cleaner");

        abh.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    m = dataSnapshot.child(cc).child("msg").getValue().toString();
                    i = Integer.parseInt(cc);
                    if(!m.equals("")) {
                        addd(i, m);
                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void reg() {

        Intent iu = new Intent(this, cleaner_details.class);
        iu.putExtra(EXTRA_TEXT,cc);
        startActivity(iu);
    }

    public void addd(int z, String x) {
        final String d = String.valueOf(z);
        TableLayout tl = (TableLayout) findViewById(R.id.works);
        TableRow tr1 = new TableRow(this);
        TextView trr = new TextView(this);
        //d = "   " + d;
        trr.setText(x);
        trr.setTextSize(25);
        trr.setGravity(Gravity.CENTER);
        Button trrr = new Button(this);
        //x = "" + x;
        trrr.setText("Accept");
        trrr.setTextSize(25);
        trrr.setGravity(Gravity.CENTER);
        //trrr.setId(z);
        tr1.addView(trr);
        tr1.addView(trrr);
        tl.addView(tr1);
        trrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference yt;
                yt = FirebaseDatabase.getInstance().getReference().child("cleaner").child(cc).child("msg");
                yt.setValue("");

                reg();
            }
        });

    }
    public void uploadpic(View view) {
        TextView t2 = (TextView) findViewById(R.id.c1);
        t2.setText("usable");
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ImageBack);
        TextView t24 = (TextView) findViewById(R.id.c1);
        t24.setText("usable");
       // cleaned();

    }

    public void cleaned() {
        final DatabaseReference rref;
        rref = FirebaseDatabase.getInstance().getReference().child("cleaner").child(cc);
        rref.child("status").setValue("ua");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageBack){
            if(requestCode == RESULT_OK){
                Uri ImageData = data.getData();
                final StorageReference Imagename = Folder.child("image"+ImageData.getLastPathSegment());
                Toast.makeText(cleaner_details.this,"Uploaded",Toast.LENGTH_SHORT).show();
            }
        }
    }


}
