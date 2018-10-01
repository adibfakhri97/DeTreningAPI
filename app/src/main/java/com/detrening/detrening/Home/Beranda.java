package com.detrening.detrening.Home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.detrening.detrening.Authentication.Login;
import com.detrening.detrening.FreeChatDir.FreeChat;
import com.detrening.detrening.Profil.ProfilInfo;
import com.detrening.detrening.R;
import com.detrening.detrening.Tips.TipsTrik;
import com.detrening.detrening.Workout.WorkOut;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.snapshot.DoubleNode;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Beranda extends AppCompatActivity {
    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase firebaseInstance;
    DatabaseReference firebaseDatabase;
    private FusedLocationProviderClient client;
    public String lat;
    public String lon;
    TextView infoNama;
    ImageView infoFoto;

    public static String emailUser, infoUser;
    FirebaseUser firebaseUser;
    Firebase mRef;

    Button btnTips, btnChat, btnProg;
    private TextView mDate, mTemp, mCity, mWeather;

    DatabaseReference databaseReference;
    String uID;
    public static final String Database_Path = "DeTrening";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("DeTrening");

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);
        mCity = (TextView) findViewById(R.id.kotaWet);
        mTemp = (TextView) findViewById(R.id.suhuWet);
        mDate = (TextView) findViewById(R.id.tanggalWet);
        mWeather = (TextView) findViewById(R.id.weather_desc);


        nAuth = FirebaseAuth.getInstance();
        firebaseUser = nAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        if (firebaseUser==null){
            startActivity(new Intent(Beranda.this, Login.class));
            finish();
        } else {
            uID = firebaseUser.getUid();
            emailUser = firebaseUser.getEmail().toString();
        }


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null){
                    startActivity(new Intent(Beranda.this, Login.class));
                    finish();
                }
            }
        };


        btnChat = (Button) findViewById(R.id.btnFreeChat);
        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, FreeChat.class);
                startActivity(intent);
            }
        });

        btnTips = (Button) findViewById(R.id.btnTips);
        btnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, TipsTrik.class);
                startActivity(intent);
            }
        });

        btnProg = (Button) findViewById(R.id.btnProgram);
        btnProg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, WorkOut.class);
                startActivity(intent);
            }
        });

        find_weather();



    }



    public void fungsiLogout(){
        nAuth.signOut();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.beranda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.nav_profileInfo:
                Toast.makeText(Beranda.this, "Profile", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(Beranda.this, ProfilInfo.class);
                startActivity(intent2);
                break;
            case  R.id.nav_logOut:
                Toast.makeText(Beranda.this, "Logout", Toast.LENGTH_SHORT).show();
                fungsiLogout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume(){
        super.onResume();

    }
    @Override
    protected void onStart(){
        super.onStart();
        nAuth.addAuthStateListener(authStateListener);

    }
    @Override
    protected void onStop(){
        super.onStop();
        if (authStateListener != null){
            nAuth.removeAuthStateListener(authStateListener);
        }

    }

    public void find_weather()
    {

        if (ActivityCompat.checkSelfPermission(Beranda.this,ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ){
            return;
        }

        client.getLastLocation().addOnSuccessListener(Beranda.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                lat = Double.toString(location.getLatitude());
                Log.d("latitude", lat);

                lon = Double.toString(location.getLongitude());
                Log.d("longitude", lon);
                String url ="https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=c8fd3e7089875c5175d3f5bee1691a56&units=metric";

                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try
                        {
                            JSONObject main_object = response.getJSONObject("main");
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object = array.getJSONObject(0);
                            String temp = String.valueOf(main_object.getDouble("temp"));
                            String description = object.getString("description");
                            String city = response.getString("name");

                            //  t1_temp.setText(temp);
                            mCity.setText(city);

                            Calendar calendar = Calendar.getInstance();
                            Date date = calendar.getTime();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy ");
                            String formatted_date = sdf.format(date);

                            mDate.setText(formatted_date);

                            double temp_int = Double.parseDouble(temp);
                            double centi = (temp_int - 32) /1.8000;
                            centi = Math.round(centi);
                            int i = (int)centi;
                            mTemp.setText(String.valueOf(temp_int));

                            mWeather.setText(description);


                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );
                RequestQueue queue = Volley.newRequestQueue(Beranda.this);
                queue.add(jor);
            }
        });



    }


    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
