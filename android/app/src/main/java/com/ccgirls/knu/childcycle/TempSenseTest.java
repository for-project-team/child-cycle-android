package com.ccgirls.knu.childcycle;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by uran on 2016. 9. 30..
 */


class TempSenserTest extends AppCompatActivity implements SensorEventListener {

    private static final int SHAKE_THRESHOLD = 600;
    // 가속도 센서값을 출력하기 위한 TextView
    TextView tvAX = null;
    TextView tvAY = null;
    TextView tvAZ = null;
    TextView speedText = null;
    String s;
    // 센서 관리자
    SensorManager sm = null;
    // 가속도 센서
    Sensor accSensor = null;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        // 객체 할당
        tvAX = (TextView) findViewById(R.id.tvAX);
        tvAY = (TextView) findViewById(R.id.tvAY);
        tvAZ = (TextView) findViewById(R.id.tvAZ);

        speedText = (TextView) findViewById(R.id.speedTxt);

        // SensorManager 인스턴스를 가져옴
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        // 가속도 센서
        accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    @Override
    public void onResume() {
        super.onResume();
        // 가속도 센서 리스너 오브젝트를 등록
        sm.registerListener(this, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 센서에서 이벤트 리스너 분리
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;

                System.out.println("스피드 스트링 변환 전 : " + speed);

                if (speed > SHAKE_THRESHOLD) {

                }

                last_x = x;
                last_y = y;
                last_z = z;

                s = String.valueOf(speed);

                System.out.println("스피드 : " + speed);



                speedText.setText(s);


            }
        }

        tvAX.setText(String.valueOf(sensorEvent.values[0]));
        tvAY.setText(String.valueOf(sensorEvent.values[1]));
        tvAZ.setText(String.valueOf(sensorEvent.values[2]));




//        switch (event.sensor.getType()) {
//            case Sensor.TYPE_ACCELEROMETER:
//                tvAX.setText(String.valueOf(event.values[0]));
//                tvAY.setText(String.valueOf(event.values[1]));
//                tvAZ.setText(String.valueOf(event.values[2]));
//                break;
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
