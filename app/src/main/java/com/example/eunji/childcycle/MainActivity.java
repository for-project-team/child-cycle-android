package com.example.eunji.childcycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton imgbtn1, imgbtn2, imgbtn3;
    private TextView txtview1, txtview2, txtview3;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private void _InitUi() {

        imgbtn1 = (ImageButton) findViewById(R.id.add_user1);
        imgbtn2 = (ImageButton) findViewById(R.id.add_user2);
        imgbtn3 = (ImageButton) findViewById(R.id.add_user3);

        txtview1 = (TextView) findViewById(R.id.user_name1);
        txtview2 = (TextView) findViewById(R.id.user_name2);
        txtview3 = (TextView) findViewById(R.id.user_name3);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);


        _InitUi();


        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);


    }

    //클릭 리스너

    public void btnClick(View v){
        Intent intent = new Intent(getApplicationContext(), AdduserActivity.class);
        startActivity(intent);

    }

    public void txtClick(View v) {

        new AlertDialog.Builder(this)
                .setTitle("안전한 자전거를 시작합니다.")
                .setMessage("'ㅇㅇㅇ'이 맞습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(getApplicationContext(), PrepareActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("아니오", null)
                .show();
        
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_main) {
            Intent intent1 = new Intent(getApplicationContext(),RidingMainActivity.class);
            startActivity(intent1);

        } else if (id == R.id.drawer_history) {

        } else if (id == R.id.drawer_setting) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
