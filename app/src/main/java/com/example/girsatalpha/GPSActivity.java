package com.example.girsatalpha;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GPSActivity extends AppCompatActivity implements IBaseGps {
    private String hereLocation(Location location){
        return "Lat:" + location.getLatitude() +"\n" + "Long:" + location.getLongitude();
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onGpsStatusChanged(int event) {

    }

    private static final int PERMISSION_LOCATION = 1000;
    Button btnShowLocation;
    TextView tvLocation, titel4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gpsactivity);
        btnShowLocation = findViewById(R.id.btnShowLocation);
        tvLocation = findViewById(R.id.tvLocation);
        titel4 = findViewById(R.id.titel4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check for location permission
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
                }
                else{
                    showLocation();
                }
            }
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
                if(requestCode == PERMISSION_LOCATION){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        showLocation();
                    }
                    else{
                        Toast.makeText(GPSActivity.this, "Permission not granted!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
            @SuppressLint("MissingPermission")
            private void showLocation(){
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                //check if gps enabled
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    //start locating
                    tvLocation.setText("Loading location...");
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, GPSActivity.this);
                }
                else{
                    //enable gps
                    Toast.makeText(GPSActivity.this, "Enable GPS!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@Nullable MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("Enter Data Activity")){
            Intent intent = new Intent(this, EnterDataActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Main Activity")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Show Data")){
            Intent intent = new Intent(this, ShowData.class);
            startActivity(intent);
        }
        else if(st.equals("Sound Activity")){
            Intent intent = new Intent(this, SoundActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Time Picker Activity")){
            Intent intent = new Intent(this, TimePickerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}