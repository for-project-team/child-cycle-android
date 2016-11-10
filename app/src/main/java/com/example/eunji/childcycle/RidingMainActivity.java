package com.example.eunji.childcycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eunji.childcycle.dto.RidingDataDTO;
import com.example.eunji.childcycle.dto.SensingDTO;
import com.example.eunji.childcycle.urlconnection.HttpClientHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Eunji on 2016. 9. 25..
 */

public class RidingMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button button_stop, button_pause;
    private TextView riding_time, today_weather = null, riding_date, weather_temp, riding_distance, riding_speed;
    private ImageView handle_aram, speed_aram, distance_aram, warning, error;
    private Handler handler;
    private boolean warning_bool = true;
    private RidingDataDTO ridingDataDTO;
    private static final String TAG = "Hanium";
    private String nickname;
    private DrawerLayout drawer;

    // 주행시간
    public static int num = 0;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

    //

    String sensing[] = {};
    String sensor = "18;123.43;456.75";
    String sensor2 = "14;124.34;0.0;";
    String sensor3 = "16;45.67;0.0";
    String sensor4 = "5;145.435;456.3657";
    String sensor5 = "156;4354.456;456.23";

    SensingDTO sensingDTO1, sensingDTO2, sensingDTO3, sensingDTO4, sensingDTO5;
    ArrayList<SensingDTO> array = new ArrayList<SensingDTO>();

    View v;

    private Date cDate;
    private String fDate;

    private Toolbar toolbar;
    // 주행 시간 타이머 핸들러
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            int hours = mins / 60;
            secs = secs % 60;

            riding_time.setText("" + String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
            customHandler.postDelayed(this, 0);
        }
    };

    private void _InitUi() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        button_stop = (Button) findViewById(R.id.button_stop);
        button_pause = (Button) findViewById(R.id.button_pause);

        riding_time = (TextView) findViewById(R.id.riding_time);
        today_weather = (TextView) findViewById(R.id.today_weather);
        riding_date = (TextView) findViewById(R.id.riding_date);
        weather_temp = (TextView) findViewById(R.id.weather_temp);
        riding_distance = (TextView) findViewById(R.id.riding_distance);
        riding_speed = (TextView) findViewById(R.id.riding_speed);

        handle_aram = (ImageView) findViewById(R.id.handle_aram);
        speed_aram = (ImageView) findViewById(R.id.speed_aram);
        distance_aram = (ImageView) findViewById(R.id.distance_aram);

        cDate = new Date();
        fDate = new SimpleDateFormat("yyyy년 MM월 dd일 (E)").format(cDate);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riding_main);

        _InitUi();

        riding_date.setText(fDate);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(" ChildCycle");
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);

//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (drawer.isDrawerOpen(Gravity.END)) {
//                    drawer.closeDrawer(Gravity.END);
//                } else {
//                    drawer.openDrawer(Gravity.END);
//                }
//            }
//        });

//        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
//        actionBar.setTitle("ChildCycle");
//        actionBar.setDisplayHomeAsUpEnabled(true);


        sensingDTO1 = new SensingDTO();
        sensingDTO2 = new SensingDTO();
        sensingDTO3 = new SensingDTO();
        sensingDTO4 = new SensingDTO();
        sensingDTO5 = new SensingDTO();

        ridingDataDTO = new RidingDataDTO();
        sensing = sensor.split(";");
        sensingDTO1.setUltrasonic(sensing[0]);
        sensingDTO1.setlFsr(sensing[1]);
        sensingDTO1.setrFsr(sensing[2]);

        sensing = sensor2.split(";");
        sensingDTO2.setUltrasonic(sensing[0]);
        sensingDTO2.setlFsr(sensing[1]);
        sensingDTO2.setrFsr(sensing[2]);

        sensing = sensor3.split(";");
        sensingDTO3.setUltrasonic(sensing[0]);
        sensingDTO3.setlFsr(sensing[1]);
        sensingDTO3.setrFsr(sensing[2]);

        sensing = sensor4.split(";");
        sensingDTO4.setUltrasonic(sensing[0]);
        sensingDTO4.setlFsr(sensing[1]);
        sensingDTO4.setrFsr(sensing[2]);

        sensing = sensor5.split(";");
        sensingDTO5.setUltrasonic(sensing[0]);
        sensingDTO5.setlFsr(sensing[1]);
        sensingDTO5.setrFsr(sensing[2]);

        array.add(sensingDTO1);
        array.add(sensingDTO2);
        array.add(sensingDTO3);
        array.add(sensingDTO4);
        array.add(sensingDTO5);

//        sensing();

        Intent intent = getIntent();
//        nickname = intent.getExtras().getString("nickname");
//        Log.i(TAG, "RidingMainActivity " + nickname);

//        handler = new Handler();
//        handler.postDelayed(runable,3000);

//        private Runnable runable = new Runnable() {
//            @Override
//            public void run() {
//                if(warning_bool) {
//                    handle_aram.setAlpha(0);
//                    warning_bool = false;
//                } else {
//                    handle_aram.setAlpha(1000);
//                    warning_bool = true;
//                }
//            }
//        };
//
//        // 임의 핸들러 작성
//        handler.post(runable);

//        handle_aram.setVisibility(View.INVISIBLE);
//        if()

    }

    // 시작, 일시정지 버튼 표시
    public void pauseClick(View v) {
        ++num;

        if (num % 2 == 1) {
            button_pause.setText("시작");

            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
        } else {
            button_pause.setText("일시정지");

            startTime = SystemClock.uptimeMillis();
            customHandler.postDelayed(updateTimerThread, 0);
        }

    }

    // 주행 시간 타이머 핸들러
//    private Runnable updateTimerThread = new Runnable() {
//        public void run() {
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//            updatedTime = timeSwapBuff + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//            int mins = secs / 60;
//            int hours = mins / 60;
//            secs = secs % 60;
//
//            riding_time.setText("" + String.format("%02d", hours) + ":" + String.format("%02d", mins) + ":" + String.format("%02d", secs));
//            customHandler.postDelayed(this, 0);
//        }
//    };


    public void stopClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("")
                .setMessage("정말로 운행을 종료할까요?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertQuery();
                        Intent intent = new Intent(getApplicationContext(), FinishRidingActivity.class);
                        intent.putExtra("time", ridingDataDTO.getRidingTime()); // intent로 다음 화면으로 값 전송
                        intent.putExtra("distance", ridingDataDTO.getTotalDistance());
                        intent.putExtra("nickname", ridingDataDTO.getNickname());
                        startActivity(intent);

                    }
                })
                .setNegativeButton("아니오", null)
                .show();

    }

    // DTO 데이터 삽입
    public void insertQuery() {
        ridingDataDTO.setTotalDistance(riding_distance.getText().toString());
        ridingDataDTO.setRidingTime(riding_time.getText().toString());
        ridingDataDTO.setCalorie("456");
        ridingDataDTO.setAvgVelocity(riding_speed.getText().toString());
        ridingDataDTO.setSafetyCnt(0);
        ridingDataDTO.setWarningCnt(0);
        ridingDataDTO.setNickname(nickname);

        postData("http://14.63.213.62:3000/ridingdata", ridingDataDTO);
    }

    /* 라이딩 데이터 DB insert */
    public void postData(String url, RidingDataDTO sendData) {
        final RequestParams params = new RequestParams();
        params.put("totalDistance", sendData.getTotalDistance());
        params.put("avgVelocity", sendData.getAvgVelocity());
        params.put("calorie", sendData.getCalorie());
        params.put("ridingTime", sendData.getRidingTime());
        params.put("safetyCnt", sendData.getSafetyCnt());
        params.put("warningCnt", sendData.getWarningCnt());
        params.put("nickname", sendData.getNickname());

        // url 및 전송된 데이터 확인 테스트
        Log.d(TAG, "url : " + url);

        HttpClientHelper.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (statusCode == 201) {
                    Toast.makeText(getApplicationContext(), "라이딩 데이터 저장 완료", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "onSuccess statusCode : " + statusCode + "\nresponse" + response);
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure statusCode : " + statusCode);
                Log.d(TAG, "throwable" + throwable + " errorResponse" + errorResponse);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        boolean bFinish = intent.getBooleanExtra("FinishSelf", false);
        if (bFinish)
            finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            //하드웨어 뒤로가기 버튼에 따른 이벤트 설정
            case KeyEvent.KEYCODE_BACK:

                new AlertDialog.Builder(this)
                        .setTitle("프로그램 종료")
                        .setMessage("프로그램을 종료 하시겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 앱 종료.
                                Intent clearTop = new Intent(getApplicationContext(), MainActivity.class);
                                clearTop.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                clearTop.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                clearTop.putExtra("FinishSelf", true);
                                startActivity(clearTop);
                                finish();

                            }
                        })

                        .setNegativeButton("아니오", null)
                        .show();
                break;

            default:
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_main) {

            Intent intent1 = new Intent(getApplicationContext(), RidingMainActivity.class);
            startActivity(intent1);
        } else if (id == R.id.drawer_history) {
            Intent intent1 = new Intent(getApplicationContext(), RecordTableActivity.class);
            startActivity(intent1);
        } else if (id == R.id.drawer_setting) {
            Intent intent2 = new Intent(getApplicationContext(), ContentsSettingActivity.class);
            startActivity(intent2);
        }

        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


//    public void getUrl() {
//        String getKmaDataUrl = "http://14.63.213.62:3000/weather";
//        new HttpTask().execute(getKmaDataUrl);
//    }
//    //kma-js 라이브러리
//    public void getKmaJsWeather() throws JSONException {
//        //서버 요청
//
//        String str = today_weather.getText().toString();
//
//
//        JSONObject jObj = new JSONObject(str);
//        today_weather.append(info.time);
//    }



