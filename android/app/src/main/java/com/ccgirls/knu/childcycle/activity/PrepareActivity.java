package com.ccgirls.knu.childcycle.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccgirls.knu.childcycle.R;

/**
 * Created by Eunji on 2016. 9. 11..
 */

public class PrepareActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "Hanium";
    Fragment fragment;
    String s[] = {"헬멧을 착용하세요", "보호장구를 착용하세요", "자전거에 탑승하세요"};
    String s1[] = {"착용완료", "확인완료"};
    String nickname;
    private Button btn;
    private TextView txtview1;
    private ImageView imgview, imgview1;
    private Button strkbtn[] = new Button[4];
    private Drawable strkbtn_toggle[] = new Drawable[2];
    private MediaPlayer mp3;//음성파일
    private Animation anim;
    private int count = 0;
    private Toolbar toolbar;

    private void initUi(){

        txtview1 = (TextView) findViewById(R.id.text_change);
        imgview = (ImageView) findViewById(R.id.prepare_image0);
        imgview1 = (ImageView) findViewById(R.id.prepare_image1);

        btn = (Button) findViewById(R.id.next_button);

        strkbtn[0] = (Button) findViewById(R.id.prepare_stroke1);
        strkbtn[1] = (Button) findViewById(R.id.prepare_stroke2);
        strkbtn[2] = (Button) findViewById(R.id.prepare_stroke3);
        strkbtn[3] = (Button) findViewById(R.id.prepare_stroke4);

        strkbtn_toggle[0] = getResources().getDrawable(R.drawable.stroke_button_clicked);
        strkbtn_toggle[1] = getResources().getDrawable(R.drawable.stroke_button);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        initUi();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(" ChildCycle");
//        toolbar.setLogo(R.mipmap.hamburger);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
//        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

        Intent intent = getIntent();
        nickname = intent.getExtras().getString("nickname");
        Log.i(TAG, "PrepareActivity " + nickname);

        imgview.setImageResource(R.drawable.pre_1_1);
        imgview1.setImageResource(R.drawable.pre_1_2);
        anim = AnimationUtils.loadAnimation(this, R.anim.move);
        imgview1.startAnimation(anim);

        mp3 = MediaPlayer.create(PrepareActivity.this, R.raw.voicehelmet);

        mp3.start();

/*
        strkbtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//circleButton_1
                strkbtn[0].setBackground(strkbtn_toggle[0]);

                for (int i = 1; i < 4; i++)
                    strkbtn[i].setBackground(strkbtn_toggle[1]);

                txtview1.setText(s[0]);
                btn.setText(s1[0]);

                imgview.setImageResource(R.drawable.pre_1_1);
                imgview1.setImageResource(R.drawable.pre_1_2);
                imgview.clearAnimation();
                anim= AnimationUtils.loadAnimation(getApplication(),R.anim.move);
                imgview1.startAnimation(anim);

                mp3.reset();
                mp3 = MediaPlayer.create(getApplicationContext(), R.raw.voicehelmet);
                mp3.start();

            }
        });

        strkbtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//circleButton_2

                for (int i = 0; i < 4; i++) {
                    if (i == 1)
                        strkbtn[i].setBackground(strkbtn_toggle[0]);
                    else
                        strkbtn[i].setBackground(strkbtn_toggle[1]);
                }

                txtview1.setText(s[1]);
                btn.setText(s1[0]);

                imgview.setImageResource(R.drawable.pre_2_1);
                imgview1.setImageResource(R.drawable.pre_2_2);
                imgview.clearAnimation();
                anim = AnimationUtils.loadAnimation(getApplication(), R.anim.blink);
                imgview1.startAnimation(anim);

                mp3.reset();
                mp3 = MediaPlayer.create(getApplicationContext(), R.raw.voiceequip);
                mp3.start();

            }
        });

        strkbtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//circleButton_3

                for (int i = 0; i < 4; i++) {
                    if (i == 2)
                        strkbtn[i].setBackground(strkbtn_toggle[0]);
                    else
                        strkbtn[i].setBackground(strkbtn_toggle[1]);
                }

                txtview1.setText(s[2]);
                btn.setText(s1[0]);

                mp3.reset();
                imgview.setImageResource(R.drawable.pre_3_1);
                imgview1.setImageResource(0);
                anim = AnimationUtils.loadAnimation(getApplication(), R.anim.ride);
                imgview.startAnimation(anim);
                mp3 = MediaPlayer.create(getApplicationContext(), R.raw.voiceride);
                mp3.start();


            }
        });

        strkbtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//circleButton_4

                fragment = new PrepareFourthActivity();

                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

                mp3.reset();
                mp3 = MediaPlayer.create(getApplicationContext(), R.raw.weatherbgm);
                mp3.start();

            }
        });

*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = btn.getText().toString();
                String text = txtview1.getText().toString();

                if (count == 3) {

                    //mp3.stop();

                    Intent intent1 = new Intent(getApplicationContext(), RidingMainActivity.class);
                    startActivity(intent1);
//                    fragment = new RidingFragment();
//
//                    if (fragment != null) {
//                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.content_frame, fragment);
//                        ft.commit();
//                    }


                    Intent intent = new Intent(getApplicationContext(), RidingMainActivity.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                }

                if (s[0].equals(text)) {
                    count++;

                    for (int i = 0; i < 4; i++) {

                        if (i == 1)
                            strkbtn[i].setBackground(strkbtn_toggle[0]);
                        else
                            strkbtn[i].setBackground(strkbtn_toggle[1]);

                    }

                    txtview1.setText(s[1]);
                    btn.setText(s1[0]);

                    imgview.setImageResource(R.drawable.pre_2_1);
                    imgview1.setImageResource(R.drawable.pre_2_2);
                    anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                    imgview1.startAnimation(anim);

                    mp3.reset();
                    mp3 = MediaPlayer.create(getApplicationContext(), R.raw.voiceequip);
                    mp3.start();

                } else if (s[1].equals(text)) {
                    count++;

                    for (int i = 0; i < 4; i++) {
                        if (i == 2)
                            strkbtn[i].setBackground(strkbtn_toggle[0]);
                        else
                            strkbtn[i].setBackground(strkbtn_toggle[1]);
                    }

                    txtview1.setText(s[2]);
                    btn.setText(s1[0]);

                    imgview.setImageResource(R.drawable.pre_3_1);
                    imgview1.setImageResource(0);
                    anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.ride);
                    imgview.startAnimation(anim);

                    mp3.reset();
                    mp3 = MediaPlayer.create(getApplicationContext(), R.raw.voiceride);
                    mp3.start();

                } else if (s[2].equals(text)) {

//                    setContentView(R.layout.activity_prepare_fourth);
                    fragment = new PrepareFourthActivity();

                    if (fragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, fragment);
                        ft.commit();
                    }


                    mp3.reset();
                    mp3 = MediaPlayer.create(getApplicationContext(), R.raw.weatherbgm_final);
                    mp3.start();

                }

            }
        });

    }
    /*안됨*/
   /* private void stopPlaying() {
        if (mp3 != null) {
            mp3.stop();
            mp3.release();
            mp3 = null;
        }
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

//    뒤로가기 버튼 막기
//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//    }
}
