package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private Button button;
private Button log;
int r;
int t;
int count = 0;
int cle = 0;
int flag =0;
String uid[]= new String[9999];
String ups[]= new String[9999];
String cid[]= new String[9999];
String cps[]= new String[9999];
EditText usn, psw;
public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usn = (EditText) findViewById(R.id.username);
        psw = (EditText) findViewById(R.id.password);
        log = (Button) findViewById(R.id.login);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

    }

    public void check(){
        if (flag == 0){
            String u = usn.getText().toString();
            String p = psw.getText().toString();
            if(u.equals("shri") && p.equals("pai")){
                openadmin();
                flag = 0;
            }
            else{
                flag = 1;
            }
        }
        else if(flag == 1) {
            final DatabaseReference reff;
            reff = FirebaseDatabase.getInstance().getReference().child("login");
            reff.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    r = (int) dataSnapshot.getChildrenCount();
                    for (int c = 1; c <= r; c++) {
                        reff.child(String.valueOf(c)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                uid[count] = dataSnapshot.child("ID").getValue().toString();
                                ups[count] = dataSnapshot.child("PASS").getValue().toString();
                                if (usn.getText().toString().equals(uid[count]) && psw.getText().toString().equals(ups[count])) {


                                    openadminmap();
                                    flag = 0;

                                } else {
                                    flag = 2;
                                }
                                count = count + 1;

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        else if(flag == 2) {
            final DatabaseReference trr;
            trr = FirebaseDatabase.getInstance().getReference().child("cleaner");
            trr.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    t = (int) dataSnapshot.getChildrenCount();
                    for (int w = 1; w <= t; w++) {
                        trr.child(String.valueOf(w)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                cid[cle] = dataSnapshot.child("USER").child("NAME").getValue().toString();
                                cps[cle] = dataSnapshot.child("PASS").getValue().toString();
                                if (usn.getText().toString().equals(cid[cle]) && psw.getText().toString().equals(cps[cle])) {


                                    opencleanmap(cle);
                                    flag = 0;

                                }
                                cle = cle + 1;

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


    public void openadminmap(){

        String  userr = usn.getText().toString();

        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra(EXTRA_TEXT, userr);
        intent.putExtra(EXTRA_NUMBER, "1");
        startActivity(intent);
    }

    public void openadmin(){

        String  userr = "shri";

        Intent intent = new Intent(this, alt.class);
        intent.putExtra(EXTRA_TEXT, userr);
        startActivity(intent);
    }

    public void opencleanmap(int r){
        int p = r+1;
        String  userr = String.valueOf(p);

        Intent intent = new Intent(this, cleaner_details.class);
        intent.putExtra(EXTRA_TEXT, userr);
        startActivity(intent);
    }



}
