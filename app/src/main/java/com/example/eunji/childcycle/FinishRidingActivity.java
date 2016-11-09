package com.example.eunji.childcycle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 10. 9..
 */

public class FinishRidingActivity extends AppCompatActivity{

    private Button total_history;
    private TextView riding_distance, riding_time;

    private String nickname;

    private Toolbar toolbar;

    private void _InitUi(){

        total_history = (Button) findViewById(R.id.total_history);

        riding_distance = (TextView) findViewById(R.id.riding_distance);
        riding_time = (TextView) findViewById(R.id.riding_time);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishriding);

        _InitUi();

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        toolbar.setTitle(" ChildCycle");
        toolbar.setLogo(R.mipmap.hamburger);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);

        // 오늘의 주행기록 표시
        Intent intent = getIntent();
        riding_time.setText(intent.getExtras().getString("time"));
        riding_distance.setText(intent.getExtras().getString("distance") + "km");
        nickname = intent.getExtras().getString("nickname");

//        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
//        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

    }

    public void historyClick(View v){
        Intent intent = new Intent(getApplicationContext(), RecordTableActivity.class);
        intent.putExtra("nickname", nickname);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
