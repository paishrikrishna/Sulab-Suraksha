package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class display_box extends AppCompatActivity {
    private StorageReference Folder;

    private static  final int ImageBack=1;
    ImageView image1,image2;
    String co;
    String me,tex;
    int coty=0;
    int count=0,q,cout;
    String url="1";
    String[] g = new String [999];
    String r,e,p,t,h,te,v,con,text;
    int z;
    int j;
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_box);
        Intent intent   =   getIntent();



        final String textg = intent.getStringExtra(MapsActivity.EXTRA_TEXT);
        if(!textg.isEmpty()) {
            text = textg;
        }
        else{
            final String texd = intent.getStringExtra(display_box.EXTRA_TEXT);
            text = texd;
        }
        final String texm = intent.getStringExtra(MapsActivity.EXTRA_NUMBER);
        if(!texm.isEmpty()){
            tex = texm;
        }
        else {
            final String tegd = intent.getStringExtra(display_box.EXTRA_NUMBER);
            tex = tegd;
        }


        TextView spd = (TextView) findViewById(R.id.shaptan); // TOILET LOCATION
        spd.setText(text);
        TextView spe = (TextView) findViewById(R.id.rr);      // ACCESS PRIORITY
        spe.setText(tex);
        final TextView methane = (TextView) findViewById(R.id.meth);
        final TextView carbon = (TextView) findViewById(R.id.co);
        final TextView wa = (TextView) findViewById(R.id.water);
        final TextView con = (TextView) findViewById(R.id.cond);
        final TextView rty = (TextView) findViewById(R.id.hs);
        final TextView ytr = (TextView) findViewById(R.id.te);
        final TextView urty = (TextView) findViewById(R.id.ute);
        final TextView uytr = (TextView) findViewById(R.id.uhu);
        final TextView uytc = (TextView) findViewById(R.id.acod);
        final TextView uytch = (TextView) findViewById(R.id.wtr);
        final TextView uyv = (TextView) findViewById(R.id.vi);
        final TextView cr = (TextView) findViewById(R.id.cre);
        TextView tyk = (TextView) findViewById(R.id.ksd);
        tyk.setText("This is How Toilet Looks like \n Parameters :");
        image1 = findViewById(R.id.im1);
        image2 = findViewById(R.id.im2);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loim();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loim();
            }
        });

        if(tex.equals("admin")){
            LinearLayout content = (LinearLayout) findViewById(R.id.oip);
            content.setVisibility(View.VISIBLE);
            LinearLayout coment = (LinearLayout) findViewById(R.id.com);
            coment.setVisibility(View.INVISIBLE);
            TableLayout tl = (TableLayout) findViewById(R.id.ti);
            tl.setVisibility(View.INVISIBLE);



        }
        else if(tex.equals("user")){
            LinearLayout content = (LinearLayout) findViewById(R.id.oip);
            content.setVisibility(View.GONE);
            LinearLayout coment = (LinearLayout) findViewById(R.id.com);
            coment.setVisibility(View.VISIBLE);
            TableLayout tl = (TableLayout) findViewById(R.id.ti);
            tl.setVisibility(View.VISIBLE);


        }
        else if(tex.equals("cleaner")){
            ImageView p2 = (ImageView) findViewById(R.id.im2);
            ImageView p1 = (ImageView) findViewById(R.id.im1);
            p2.setVisibility(View.INVISIBLE);
            p1.setVisibility(View.INVISIBLE);
            LinearLayout content = (LinearLayout) findViewById(R.id.oip);
            content.setVisibility(View.INVISIBLE);
            LinearLayout coment = (LinearLayout) findViewById(R.id.com);
            coment.setVisibility(View.INVISIBLE);


        }


        final DatabaseReference reff;
        reff = FirebaseDatabase.getInstance().getReference().child("location");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String ur1 = dataSnapshot.child(text).child("image1").getValue().toString();
                Picasso.get().load(ur1).into(image1); // images
                String ur2 = dataSnapshot.child(text).child("image2").getValue().toString();
                Picasso.get().load(ur2).into(image2); // images
                co = dataSnapshot.child(text).child("parameters").child("ammonia").getValue().toString();
                me = dataSnapshot.child(text).child("parameters").child("methane").getValue().toString();
                 p = dataSnapshot.child(text).child("condition").getValue().toString();
                v = dataSnapshot.child(text).child("visitors").getValue().toString();
                 t = dataSnapshot.child(text).child("water").getValue().toString();
                 h = dataSnapshot.child(text).child("parameters").child("H2S").getValue().toString();
                String huy = dataSnapshot.child(text).child("parameters").child("Humidity").getValue().toString();
                 te = dataSnapshot.child(text).child("parameters").child("Temperature").getValue().toString();
                 uytch.setText(t);
                urty.setText(te+" celcius");
                uytr.setText(huy);
                rty.setText(huy);
                ytr.setText(te+" celcius");
                methane.setText(me+" ppm");
                carbon.setText(co+" ppm");
                wa.setText(t);
                con.setText(p);
                uytc.setText(p);
                uyv.setText(v);
                if(Integer.parseInt(huy)>100 || Integer.parseInt(v)>29|| Float.parseFloat(co)>20 || Float.parseFloat(me)>500 || p.equals("unusable") )
                {
                    cr.setText("Required");
                }
                else{
                    cr.setText("Not Required");
                }
                if(Integer.parseInt(huy)>100){
                    rty.setTextColor(Color.RED);

                }
                else if(Integer.parseInt(v)<101){
                    rty.setTextColor(Color.GREEN);

                }
                if(Integer.parseInt(v)>29){
                    uyv.setTextColor(Color.RED);
                }
                else if(Integer.parseInt(v)<30){
                    uyv.setTextColor(Color.GREEN);

                }
                uyv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Visitors");
                    }
                });
                methane.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Methane");
                    }
                });
                carbon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Ammonia");
                    }

                });
                rty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Humidity");
                    }

                });
                uytch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Water");
                    }

                });
                ytr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh("Temperature");
                    }

                });
                if(t.equals("low")){
                    wa.setTextColor(Color.RED);

                }
                else if(t.equals("sufficient")){
                    wa.setTextColor(Color.GREEN);
                    uytch.setTextColor(Color.GREEN);

                }
                if(Float.parseFloat(te)<40){
                    urty.setTextColor(Color.BLUE);
                    ytr.setTextColor(Color.BLUE);
                }
                else  if(Float.parseFloat(te)>39){
                    urty.setTextColor(Color.RED);
                    ytr.setTextColor(Color.RED);

                }
                if(Float.parseFloat(co)>20){
                    carbon.setTextColor(Color.RED);

                }
                else{
                    carbon.setTextColor(Color.GREEN);

                }
                if(Float.parseFloat(me)>500){
                    methane.setTextColor(Color.RED);

                }
                else{
                    methane.setTextColor(Color.GREEN);

                }
                if(p.equals("usable")){
                    uytc.setTextColor(Color.GREEN);

                }
                else{
                    uytc.setTextColor(Color.RED);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final DatabaseReference re;
        re = FirebaseDatabase.getInstance().getReference().child("location");
        re.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                TextView sp = (TextView) findViewById(R.id.shaptan);
                String d = sp.getText().toString();

                g[1] = dataSnapshot.child(d).child("reviews").child("1").getValue().toString();
                g[2] = dataSnapshot.child(d).child("reviews").child("2").getValue().toString();
                g[3] = dataSnapshot.child(d).child("reviews").child("3").getValue().toString();
                g[4] = dataSnapshot.child(d).child("reviews").child("4").getValue().toString();
                g[5] = dataSnapshot.child(d).child("reviews").child("5").getValue().toString();

                final TextView tt1 = (TextView) findViewById(R.id.rev1);
                final TextView tt2 = (TextView) findViewById(R.id.rev2);
                final TextView tt3 = (TextView) findViewById(R.id.rev3);
                final TextView tt4 = (TextView) findViewById(R.id.rev4);
                final TextView tt5 = (TextView) findViewById(R.id.rev5);


                tt1.setText(g[1]);
                tt2.setText(g[2]);
                tt3.setText(g[3]);
                tt4.setText(g[4]);
                tt5.setText(g[5]);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final DatabaseReference tr;
        TextView spdd = (TextView) findViewById(R.id.shaptan);
        String t = spdd.getText().toString();
        tr = FirebaseDatabase.getInstance().getReference().child("location").child(t);
        tr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                r = dataSnapshot.child("count").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Button imh = (Button) findViewById(R.id.ifg);
        imh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loim();
            }
        });

        Button sen = (Button) findViewById(R.id.SEND);
        sen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                j = Integer.parseInt(r);
                if (j < 5) {
                    senddata(j);
                }
                else
                {
                    j=0;
                    senddata(j);
                }
                j = j+1;
                e = String.valueOf(j);
                tr.child("count").setValue(e);

            }
        });


    }

    public void loim() {
        TextView no = (TextView) findViewById(R.id.shaptan);
        String np = String.valueOf(no.getText());
        Intent io = new Intent(this,imagesd.class);
        io.putExtra(EXTRA_TEXT,np);
        startActivity(io);
    }


    public void refresh(String j) {

        Intent intents = new Intent(this, graph.class);
        intents.putExtra(EXTRA_TEXT,j);
        startActivity(intents);

    }

    public void senddata(int r){
        int i = r+1;
        TextView txt = (TextView) findViewById(R.id.shaptan);
        String text = txt.getText().toString();
        EditText cont = (EditText) findViewById(R.id.revi);
        final String s = cont.getText().toString();
        final DatabaseReference red;
        red = FirebaseDatabase.getInstance().getReference().child("location").child(text).child("reviews");
        red.child(String.valueOf(i)).setValue(s);
        cont.setText("");

    }
    public void uploadpic(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,ImageBack);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ImageBack){
            if(requestCode == RESULT_OK){
                Uri ImageData = data.getData();
                final StorageReference Imagename = Folder.child("image"+ImageData.getLastPathSegment());
                Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                DatabaseReference imagestore = FirebaseDatabase.getInstance().getReference().child("Image");
                                HashMap<String,String> hashMap = new HashMap<>();
                                hashMap.put("1", String.valueOf(uri));
                                imagestore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(display_box.this,"Uploaded",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        });
                    }

                });
            }
        }
    }

}
