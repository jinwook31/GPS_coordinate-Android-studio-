package com.example.jinwook.androidgpstracking;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    Button btnShowLocation;
    // GPSTracker class
    GPSTracker gps;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //Parse Data Upload

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "UVARuss3a3n6uz1Kw2JQmWOnyuRsTXYcyxIjuq1G", "rncsDVK48lPfAhQDGzA2O1T0PJGDqpozhGXASDcm");

        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        //GPS Location

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button) findViewById(R.id.GPS);

        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                gps = new GPSTracker(MainActivity.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    //send data to parse
                    ParseObject GPS_android = new ParseObject("GPS_android");
                    GPS_android.put("latitude", latitude);
                    GPS_android.put("longtitude", longitude);
                    GPS_android.saveInBackground();

                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

            }
        });
    }
}
