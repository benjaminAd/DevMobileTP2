package com.example.exo3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textViewX, textViewY, textViewZ;
    private float min, moy, max, x, y, z;
    private boolean supported;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textViewX = (TextView) findViewById(R.id.textViewX);
        textViewY = (TextView) findViewById(R.id.textViewY);
        textViewZ = (TextView) findViewById(R.id.textViewZ);
        if (sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            max = sensor.getMaximumRange();
            min = max / 3;
            moy = max - min;
            supported = sensorManager.registerListener((SensorEventListener) this, sensor, sensorManager.SENSOR_DELAY_UI);
            if (!supported) sensorManager.unregisterListener((SensorEventListener) this, sensor);
        } else {
            textViewY.setText(R.string.CannotUserSensor);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        textViewX.setText(x + "");
        textViewY.setText(y + "");
        textViewZ.setText(z + "");
        if (x < min) {
            textViewX.setTextColor(Color.GREEN);
        } else if (x >= min && x < moy) {
            textViewX.setTextColor(Color.BLACK);
        } else if (x >= moy) {
            textViewX.setTextColor(Color.RED);
        }

        if (y < min) {
            textViewY.setTextColor(Color.GREEN);
        } else if (y >= min && y < moy) {
            textViewY.setTextColor(Color.BLACK);
        } else if (y >= moy) {
            textViewY.setTextColor(Color.RED);
        }

        if (z < min) {
            textViewZ.setTextColor(Color.GREEN);
        } else if (z >= min && z < moy) {
            textViewZ.setTextColor(Color.BLACK);
        } else if (z >= moy) {
            textViewZ.setTextColor(Color.RED);
        }
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