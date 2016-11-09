package com.example.eunji.childcycle;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 10. 12..
 */

public class ContentsSettingActivity extends ActionBarActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ListView setting_listview;
    private ArrayList<ListviewItem> data;
    private ArrayList<String> save_string;
    private ListviewItem user_edit, user_change, user_delete, user_logout, audio_onoff;
    private ListviewAdapter adapter;
    private TextView list_Text;
    private DrawerLayout drawer;
    private String list_text;

    private ActionBar actionBar;

    private Toolbar toolbar;

    private void _InitUi() {

        setting_listview = (ListView) findViewById(R.id.setting_listview);

        data = new ArrayList<>();

        actionBar = getSupportActionBar();


        user_change = new ListviewItem(R.drawable.ic_menu_camera, "사용자 변경");
        user_edit = new ListviewItem(R.drawable.ic_menu_camera, "프로필 수정");
        audio_onoff = new ListviewItem(R.drawable.ic_menu_camera, "음성안내");
        user_delete = new ListviewItem(R.drawable.ic_menu_camera, "사용자 삭제");
        user_logout = new ListviewItem(R.drawable.ic_menu_camera, "로그아웃");

        drawer = (DrawerLayout) findViewById(R.id.drawer);


        adapter = new ListviewAdapter(this, R.layout.ui_setting_list, data);

        toolbar = (Toolbar) findViewById(R.id.app_toolbar);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_setting);

        _InitUi();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(" ChildCycle");
//        toolbar.setLogo(R.mipmap.hamburger);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);

//        actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
//        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

        save_string = new ArrayList<String>();


        //list 생성
        data.add(user_change);
        data.add(user_edit);
        data.add(audio_onoff);
        data.add(user_delete);
        data.add(user_logout);

        setting_listview.setAdapter(adapter);
        setting_listview.setOnItemClickListener(new ListClickHandler());
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

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            list_Text = (TextView) view.findViewById(R.id.list_text);
            list_text = list_Text.getText().toString();

            if (list_text.equals(user_change.getName())) {
                actionBar.hide();

                setContentView(R.layout.content_main);
            } else if (list_text.equals(user_edit.getName())) {

                actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF5722));
                actionBar.setTitle(Html.fromHtml("<font color='#ffffff'> ChildCycle </font>"));

                setContentView(R.layout.activity_adduser);
            } else if (list_text.equals(user_delete.getName()))
                System.out.println("delete");

            else if (list_text.equals(user_logout.getName()))
                finish();

            else if (list_text.equals(audio_onoff.getName())) {
                finish();                                   // 수정요망 // 클릭되면 아이콘(이미지)를 on/off....
            }
        }

    }
}
