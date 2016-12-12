package com.ccgirls.knu.childcycle;

import android.graphics.Color;
import android.os.AsyncTask;
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

import com.ccgirls.knu.childcycle.dto.RidingDataDTO;
import com.ccgirls.knu.childcycle.urlconnection.HttpClientHelper;
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

//        intent에서 오류나서 막음
//        Intent intent = getIntent();
//        String nickname = intent.getExtras().getString("nickname");

        list = new ArrayList<>();

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
    }

    public ArrayList<RidingDataDTO> getData(String url){

        RequestParams params = new RequestParams();
        showlist = new ArrayList<RidingDataDTO>();
        Log.d(TAG, "url " + url);

        HttpClientHelper.get(url, params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try{
                    for(int i = 0;i<response.length();i++){
                        JSONObject data = response.getJSONObject(i);
                        RidingDataDTO record = new RidingDataDTO();
                        record.setDate(data.getString("date"));
                        record.setTotalDistance(data.getString("totalDistance"));
                        record.setRidingTime(data.getString("ridingTime"));
                        showlist.add(record);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                if(statusCode == 201){
                    Log.i(TAG, "라이딩데이터 불러오기 완료");
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
        });

        return showlist;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}

// 리스너
interface OnCompletionListener2 {
    void onComplete(ArrayList<RidingDataDTO> result);
}

// 비동기통신 콜백함수
class HttpTask2 extends AsyncTask<ArrayList<RidingDataDTO>, Void, ArrayList<RidingDataDTO>> {

    OnCompletionListener2 listener = null;
    ArrayList<RidingDataDTO> list = new ArrayList<>();
    String getDataUrl = "http://14.63.213.62:3000/ridingrecord";
    RecordTableActivity main = new RecordTableActivity();

    public HttpTask2() {
    }

    public HttpTask2(OnCompletionListener2 listener) {
        this.listener = listener;
    }

    protected ArrayList<RidingDataDTO> doInBackground(ArrayList<RidingDataDTO>... params) {

        if(params != null){
            main.getData(getDataUrl);
            return list;
        } else {
            return null;
        }
    }

    // 결과에 대해 호출되는 부분
    protected void onPostExecute(ArrayList<RidingDataDTO> result) {
        // result : 웹서버로부터 가져온 값
        // 리턴받은 String데이터를 EditText에 출력
        if ( listener != null)
            listener.onComplete(result);    // 리스너 호출
    }
}
