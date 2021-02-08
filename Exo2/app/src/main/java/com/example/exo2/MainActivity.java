package com.example.exo2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        String heartRate = "";
        String accelerometre = "";
        if (sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE) != null) {
            heartRate = "Capteur heart Rate OK";
        } else {
            heartRate = "Pas de capteur heart rate";
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            accelerometre = "Capteur accéléromètre OK";
        } else {
            accelerometre = "Pas de capteur accéléromètre";
        }

        Toast.makeText(this, heartRate + "\n" + accelerometre, Toast.LENGTH_LONG).show();
    }

}