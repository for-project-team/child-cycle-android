package com.example.eunji.childcycle;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by uran on 2016. 9. 30..
 */

public class TempSenseTest extends AppCompatActivity {


    private TextView tv;
    private LocationManager lm;
    private LocationListener ll;
    double mySpeed, maxSpeed;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        setContentView(tv);


        System.out.println("start program");

        maxSpeed = mySpeed = 0;
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new SpeedoActionListener();

//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
    }

    private class SpeedoActionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                mySpeed = location.getSpeed();
                if (mySpeed > maxSpeed) {
                    maxSpeed = mySpeed;
                }
                tv.setText("\nCurrent Speed : " + mySpeed + " km/h, Max Speed : "
                        + maxSpeed + " km/h");
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }


}



//public class TempSenserTest extends AppCompatActivity implements SensorEventListener {
//
//    // 가속도 센서값을 출력하기 위한 TextView
//    TextView tvAX = null;
//    TextView tvAY = null;
//    TextView tvAZ = null;
//
//    TextView speedText = null;
//    String s;
//
//    // 센서 관리자
//    SensorManager sm = null;
//    // 가속도 센서
//    Sensor accSensor = null;
//
//    private long lastUpdate = 0;
//    private float last_x, last_y, last_z;
//    private static final int SHAKE_THRESHOLD = 600;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sensor_test);
//
//        // 객체 할당
//        tvAX = (TextView) findViewById(R.id.tvAX);
//        tvAY = (TextView) findViewById(R.id.tvAY);
//        tvAZ = (TextView) findViewById(R.id.tvAZ);
//
//        speedText = (TextView) findViewById(R.id.speedTxt);
//
//        // SensorManager 인스턴스를 가져옴
//        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
//        // 가속도 센서
//        accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//
//
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        // 가속도 센서 리스너 오브젝트를 등록
//        sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        // 센서에서 이벤트 리스너 분리
//        sm.unregisterListener(this);
//    }
//
//
//    @Override
//    public void onSensorChanged(SensorEvent sensorEvent) {
//        Sensor mySensor = sensorEvent.sensor;
//
//        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
//            float x = sensorEvent.values[0];
//            float y = sensorEvent.values[1];
//            float z = sensorEvent.values[2];
//
//            long curTime = System.currentTimeMillis();
//
//            if ((curTime - lastUpdate) > 100) {
//                long diffTime = (curTime - lastUpdate);
//                lastUpdate = curTime;
//
//                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
//
//                System.out.println("스피드 스트링 변환 전 : " + speed);
//
//                if (speed > SHAKE_THRESHOLD) {
//
//                }
//
//                last_x = x;
//                last_y = y;
//                last_z = z;
//
//                s = String.valueOf(speed);
//
//                System.out.println("스피드 : " + speed);
//
//
//
//                speedText.setText(s);
//
//
//            }
//        }
//
//        tvAX.setText(String.valueOf(sensorEvent.values[0]));
//        tvAY.setText(String.valueOf(sensorEvent.values[1]));
//        tvAZ.setText(String.valueOf(sensorEvent.values[2]));
//
//
//
//
////        switch (event.sensor.getType()) {
////            case Sensor.TYPE_ACCELEROMETER:
////                tvAX.setText(String.valueOf(event.values[0]));
////                tvAY.setText(String.valueOf(event.values[1]));
////                tvAZ.setText(String.valueOf(event.values[2]));
////                break;
////        }
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }
//}
