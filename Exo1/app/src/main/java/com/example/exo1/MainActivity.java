package com.example.exo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listSensor();
    }

    private void listSensor() {
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor sensor : sensors) {
            StringBuffer sensorDesc = new StringBuffer();
            sensorDesc.append("New sensor detected: \r \n");
            sensorDesc.append("\tName: ").append(sensor.getName()).append("\r\n");
            sensorDesc.append("\tType: ").append(sensor.getType()).append("\r\n");
            sensorDesc.append("Version: ").append(sensor.getVersion()).append("\r\n");
            sensorDesc.append("Resolution: ").append(sensor.getResolution()).append("\r\n");
            sensorDesc.append("Power in mA used by this sensor while in use: ").append(sensor.getPower()).append("\r\n");
            sensorDesc.append("Vendor: ").append(sensor.getVendor()).append("\r\n");
            sensorDesc.append("Maximum range of the sensor in the sensor's unit: ").append(sensor.getMaximumRange()).append("\r\n");
            sensorDesc.append("Minimum delay allowed between two events in microsecond or zero if this sensor only returns a value when the data it's measuring changes ").append(sensor.getMinDelay()).append("\r\n");
            sensorDesc.append("------------------------------------------------\n");
            TextView tv = new TextView(this);
            tv.setText(sensorDesc);
            linearLayout.addView(tv);
        }
    }

}