package com.example.eunji.childcycle;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.eunji.childcycle.dto.RidingDataDTO;
import com.example.eunji.childcycle.urlconnection.HttpClientHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Eunji on 2016. 11. 8..
 */

public class RecordTableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Hanium";

    private TextView test_textview;
    private ArrayList<RidingDataDTO> list, showlist;

    private Toolbar toolbar;

    private void _InitUi() {
        test_textview = (TextView) findViewById(R.id.testTxt);
        toolbar = (Toolbar) findViewById(R.id.app_toolbar);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_table);

        _InitUi();

//        Intent intent = getIntent();
//        String nickname = intent.getExtras().getString("nickname");

        showlist = new ArrayList<>();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toolbar.setTitle(" ChildCycle");
//        toolbar.setLogo(R.drawable.ic_menu_white_24dp);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setBackgroundColor(0xffff5722);

//        getData("http://14.63.213.62:3000/ridingrecord", nickname);
    }

    /*
    * JSONObject data = response.getJSONObject(i);

                        RidingDataDTO record = new RidingDataDTO();
                        record.setDate(data.getString("date"));
                        record.setTotalDistance(data.getString("totalDistance"));
                        record.setRidingTime(data.getString("ridingTime"));
    *
    * */

    public void getData(String url, String sendData){
        RequestParams params = new RequestParams();
        params.put("nickname", sendData);

        Log.d(TAG, "url " + url + ",\n sendData : " + sendData);

        HttpClientHelper.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    String date = response.getString("date");
                    Log.d(TAG, "date : " + date);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    JSONObject data = response.getJSONObject(0);
                    String date = data.getString("date");
                    Log.d(TAG, "date : " + date);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure1" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure2" + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d(TAG, "onFailure3" + statusCode);
                Log.d(TAG, "throwable" + throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                String date = responseString.toString();
                Log.d(TAG, "String " + date);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
