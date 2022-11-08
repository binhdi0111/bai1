package com.binhdi0111.bka.sensorsurvey;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorProximity;
    private Sensor mSensorLight;
    private Sensor mSensorHUMIDITY;

    // TextViews to display current sensor values
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;
    private TextView mTextSensorHumidity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        List<Sensor> sensorList  =
//                mSensorManager.getSensorList(Sensor.TYPE_ALL);
//        StringBuilder sensorText = new StringBuilder();
//
//        for (Sensor currentSensor : sensorList ) {
//            sensorText.append(currentSensor.getName()).append(
//                    System.getProperty("line.separator"));
//        }
//        TextView sensorTextView = (TextView) findViewById(R.id.sensor_list);
//        sensorTextView.setText(sensorText);
        mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mTextSensorLight= (TextView) findViewById(R.id.label_light);
        mTextSensorProximity = (TextView) findViewById(R.id.label_proximity);
        mTextSensorHumidity = (TextView) findViewById(R.id.label_humidity);

        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorHUMIDITY = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        String sensor_error = getResources().getString(R.string.error_no_sensor);

        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorHUMIDITY == null) {
            mTextSensorHumidity.setText(sensor_error);
        }

    }
    @SuppressLint("StringFormatInvalid")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];
        switch (sensorType) {
            // Event came from the light sensor.
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(getResources().getString(
                        R.string.label_light, currentValue));
                // Handle light sensor
                break;
                // do nothing
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(getResources().getString(
                        R.string.label_proximity, currentValue));
                Log.d("bbbbbbbbbbbbbbbbbb", "onSensorChanged: "+currentValue);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(getResources().getString(
                        R.string.label_humidity, currentValue));
                Log.d("aaaaaaaaaaaaaaaaaa", "onSensorChanged: "+currentValue);
                break;
        }
        Log.d("hihihihihihihihihhihhihihihi", "onSensorChanged: "+currentValue);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorHUMIDITY != null) {
            mSensorManager.registerListener(this, mSensorHUMIDITY,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }
}