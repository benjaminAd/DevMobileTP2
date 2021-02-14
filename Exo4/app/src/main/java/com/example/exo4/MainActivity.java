package com.example.exo4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;

    private Sensor sensor;

    private ImageView imageView, imageView2;
    private TextView textView;

    private boolean supported;
    private float x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
//        textView = (TextView) findViewById(R.id.textView);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            supported = sensorManager.registerListener((SensorEventListener) this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            if (!supported) sensorManager.unregisterListener(this, sensor);
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        } else {
//            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        if (x > 1) imageView.setImageResource(R.drawable.leftarrow);
        if (x < -1) imageView.setImageResource(R.drawable.rightarrow);
        if (y > 1) imageView2.setImageResource(R.drawable.downarrow);
        if (y < -1) imageView2.setImageResource(R.drawable.uparrow);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        switch (accuracy) {
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
        }
        Log.d("Sensor ", sensor.getType() + ":" + accuracy);
    }
}