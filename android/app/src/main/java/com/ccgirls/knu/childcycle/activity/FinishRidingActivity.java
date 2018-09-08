package com.ccgirls.knu.childcycle.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccgirls.knu.childcycle.R;

/**
 * Created by Eunji on 2016. 10. 9..
 */

public class FinishRidingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button total_history;
    private TextView riding_distance, riding_time;

    private String nickname;

    private Toolbar toolbar;

    private DrawerLayout drawer;
    private ImageView img_ending,imgv_distance, imgv_time;

    private void initUi() {

        total_history = (Button) findViewById(R.id.total_history);
        riding_distance = (TextView) findViewById(R.id.riding_distance);
        riding_time = (TextView) findViewById(R.id.riding_time);
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer);

        img_ending=(ImageView)findViewById(R.id.ending_img) ;
        imgv_distance=(ImageView)findViewById(R.id.imgv_distance);
        imgv_time=(ImageView)findViewById(R.id.imgv_time);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishriding);

        initUi();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(" ChildCycle");
//        toolbar.setLogo(R.mipmap.hamburger);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);

        // 오늘의 주행기록 표시
        Intent intent = getIntent();
        riding_time.setText(intent.getExtras().getString("time"));
        riding_distance.setText(intent.getExtras().getString("distance") + "km");
        nickname = intent.getExtras().getString("nickname");

//        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
//        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

        img_ending.setImageResource(R.drawable.pre_3_1);
        imgv_distance.setImageResource(R.mipmap.ic_location);
        imgv_time.setImageResource(R.mipmap.ic_time);

    }

    public void historyClick(View v) {
        Intent intent = new Intent(getApplicationContext(), RecordTableActivity.class);
        intent.putExtra("nickname", nickname);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_main) {

            Intent intent1 = new Intent(getApplicationContext(), RidingMainActivity.class);
            startActivity(intent1);
//            fragment = new RidingFragment();
//
//            if(fragment != null){
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, fragment);
//                ft.commit();
//            }

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

