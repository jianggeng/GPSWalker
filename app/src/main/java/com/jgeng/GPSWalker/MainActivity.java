package com.jgeng.GPSWalker;

import android.location.LocationManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnSet = (Button)findViewById(R.id.btn_set);
//        final EditText latitudeView = (EditText)findViewById(R.id.latitude_input);
//        final EditText longitudeView = (EditText)findViewById(R.id.longitude_input);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSLinearRoutine routine = new GPSLinearRoutine(37.402745, -122.048690,
                    37.422014, -122.083670,
                    30*60*1000, 10*1000);
                GpsRoutineRunner walker = new GpsRoutineRunner(MainActivity.this,
                    new Handler(),
                    LocationManager.GPS_PROVIDER,
                    routine);
                walker.start();
            }
        });
    }
}
