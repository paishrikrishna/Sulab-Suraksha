package com.example.baiscac;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    String shhh ;
    String l;
    int i;
    int a;
    String se;
    String dev[]= new String[9999];
    String con[]= new String[9999];
    String adm[]= new String[9999];
    String wat[]= new String[9999];
    String ling[]= new String[9999];
    String pay[]= new String[9999];
    int count = 0;
    int flag;
    int r;
    private GoogleMap mMap;
    double lattt;
    double longii;
    Double lo;
    Double la;
    private  Marker ul;
    private Marker[] mmn = new Marker[999];
    public static final String EXTRA_TEXT   =   "com.example.application.example.EXTRA_TEXT";
    public static final String EXTRA_NUMBER   =   "com.example.application.example.EXTRA_NUMBER";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        final DatabaseReference rt;
        rt= FirebaseDatabase.getInstance().getReference().child("login");
        rt.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                a = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       Button ad = (Button) findViewById(R.id.refre); //ADMIN LOGIN BUTTON
       ad.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               admop();
           }
       });

        // BOX CODE HERE

        final TextView boox = (TextView) findViewById(R.id.boxx);

        boox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshri();
            }
        });

        final TextView beoox = (TextView) findViewById(R.id.dat);

        beoox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openshri();
            }
        });




        assert locationManager != null;
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        assert null != location;
        onLocationChanged(location);




    }

    public void admop() {
        Intent inte = new Intent(this, MapsActivity.class);
        startActivity(inte);
    }

    public void openshri() {
        TextView sp = (TextView) findViewById(R.id.boxx);
        String dat = sp.getText().toString();
        Intent intent = new Intent(this, display_box.class);
        intent.putExtra(EXTRA_TEXT, dat);
        if(flag == 1) {
            String ee = "admin";
            intent.putExtra(EXTRA_NUMBER, ee);
        }
        else if(flag == 2) {
            String ee = "cleaner";
            intent.putExtra(EXTRA_NUMBER, ee);
        }
        else if(flag == 3) {
            String ee = "user";
            intent.putExtra(EXTRA_NUMBER, ee);
        }
        startActivity(intent);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        Intent intent   =   getIntent();
        final String gende = intent.getStringExtra(gender.EXTRA_TEXT);
        final String acc = intent.getStringExtra(gender.EXTRA_NUMBER);
        Intent intentt   =   getIntent();
        final String text = intentt.getStringExtra(MainActivity.EXTRA_TEXT);


        //Add a marker in Sydney and move the camera
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(new LatLng(lattt, longii));
        builder.zoom(15);
        builder.bearing(0);
        builder.tilt(45);
        CameraPosition googlePlex = builder
                .build();


        final Button vi = (Button) findViewById(R.id.mtype);
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type = String.valueOf(mMap.getMapType());
                if(type.equals("3")){
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    vi.setText("Terrain");
                }
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                    vi.setText("Hybrid");

                }
            }
        });


        // Add a marker in Sydney and move the camera
        LatLng curr = new LatLng(lattt, longii);
        ul = mMap.addMarker(new MarkerOptions().position(curr).title("Your Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        // for admin
        final DatabaseReference y;
        y = FirebaseDatabase.getInstance().getReference().child("login");
        y.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (i=1;i<=a;i++){
                    adm[i] = dataSnapshot.child(String.valueOf(i)).child("ID").getValue().toString();
                    if(text.equals(adm[i])) {
                        flag = 1;
                        l = " ";

                        break;

                    }
                    else if(text.equals("cleaner")){
                        flag = 2;
                        l = "cleaner";

                        RelativeLayout bo = (RelativeLayout) findViewById(R.id.boox);
                        bo.setVisibility(View.INVISIBLE);
                    }

                    else{
                        flag = 3;
                        l = " ";
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // for USER




        final DatabaseReference trr;
        trr = FirebaseDatabase.getInstance().getReference().child("location");
        trr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             r = (int) dataSnapshot.getChildrenCount();

                for(int c = 1; c<=r ; c++) {
                    trr.child(String.valueOf(c)).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            lo = dataSnapshot.child("long").getValue(Double.class);
                            la = dataSnapshot.child("lat").getValue(Double.class);
                            shhh = dataSnapshot.child("supervisor").getValue().toString();
                            dev[count] = dataSnapshot.child("device").getValue().toString();
                            con[count] = dataSnapshot.child("condition").getValue().toString();
                            wat[count] = dataSnapshot.child("water").getValue().toString();
                            ling[count] = dataSnapshot.child("gender").getValue().toString();
                            pay[count] = dataSnapshot.child("pay").getValue().toString();

                            LatLng ghan = new LatLng(la, lo);
                            if(con[count].equals("unusable")) {
                               se = String.valueOf(HUE_RED);
                            }
                            else if(con[count].equals("usable")){
                                se = String.valueOf(HUE_GREEN);

                            }

                            mmn[count] = mMap.addMarker(new MarkerOptions().position(ghan).title("E-TOILET").snippet(shhh).icon(BitmapDescriptorFactory.defaultMarker(Float.parseFloat(se))));
                            String p = mmn[count].getSnippet();
                                if(!acc.equals("12")) {
                                    if (!text.equals(p) && !text.equals("user") && !text.equals("shri") && !text.equals("cleaner")) {
                                        mmn[count].remove();
                                    }
                                }
                               else if(acc.equals("12")) {
                                    if (!ling[count].equals(gende)) {
                                        mmn[count].remove();
                                    }
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




        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex));



    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    @Override
    public void onLocationChanged(Location location) {
        double loca =   location.getLongitude();
        double lon  =   location.getLatitude();
        longii = loca;
        lattt = lon;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    public boolean onMarkerClick(final Marker marker) {
        for(int i = 0 ; i < count ; i++) {
            if (marker.equals(mmn[i])) {
                String s = dev[i];
                String o = con[i];
                String w = wat[i];
                String z = pay[i];
                String sse = null;

                mmn[i].setIcon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                TextView booox = (TextView) findViewById(R.id.boxx);
                booox.setVisibility(View.INVISIBLE);
                TextView booxx = (TextView) findViewById(R.id.sta);
                booxx.setText(o);
                TextView data = (TextView) findViewById(R.id.dat);
                String p = "Water: "+w+" "+",Condition: "+o+"\n"+"Pay and use system : "+ z +"\n"+"click for more";
                data.setText(p);
                mmn[i].setIcon(BitmapDescriptorFactory.defaultMarker(Float.parseFloat(se)));
                RelativeLayout bo = (RelativeLayout) findViewById(R.id.boox);
                if(l.equals("cleaner")){
                    opencleanchart(s);
                    bo.setVisibility(View.INVISIBLE);
                }
                else{
                    bo.setVisibility(View.VISIBLE);
                }


                booox.setText(s);
            }

            else if(marker.equals(ul)){
                RelativeLayout y = (RelativeLayout) findViewById(R.id.boox);
                y.setVisibility(View.INVISIBLE);
            }
        }

        return false;
    }

    public void opencleanchart(String s) {
        String o = s;
        Intent intent = new Intent(this, cleanmap.class);
        intent.putExtra(EXTRA_NUMBER, o);
        startActivity(intent);

    }


    @Override
    public void onMapClick(LatLng latLng) {
        RelativeLayout y = (RelativeLayout) findViewById(R.id.boox);
        y.setVisibility(View.GONE);
    }
}
