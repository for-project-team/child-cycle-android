package com.example.eunji.childcycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eunji.childcycle.dto.UserDTO;
import com.example.eunji.childcycle.urlconnection.HttpClientHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Eunji on 2016. 9. 12..
 *
 * Updated by Hyesun on 2016. 10. 11..
 * 회원가입을 위한 restful api 제작 및 연결 완료
 * post 방식을 이용한 데이터 전송
 *
 */
public class AdduserActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText user_name;
    private EditText user_nickname;
    private EditText user_birthday;
    private EditText user_height;
    private EditText user_weight;
    private RadioButton woman_radiobutton, man_radiobutton;
    private RadioGroup gender_radiogroup;
    private Button add_user_finish;

    private UserDTO userDTO;

    private static final String TAG = "Hanium";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        setTitle("사용자 등록");
        _InitUi();

        HttpClientHelper.myCookieStore = new PersistentCookieStore(this);
        HttpClientHelper.myCookieStore.clear();
        HttpClientHelper.client.setCookieStore(HttpClientHelper.myCookieStore);

        userDTO = new UserDTO();


        gender_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.woman_radiobutton:
                        userDTO.setGender(1);
                        break;
                    case R.id.man_radiobutton:
                        userDTO.setGender(0);
                        break;
                }
            }
        });
    }

    private void _InitUi(){
        user_name = (EditText) findViewById(R.id.user_name);
        user_birthday = (EditText) findViewById(R.id.user_birthday);
        user_height = (EditText) findViewById(R.id.user_height);
        user_weight = (EditText) findViewById(R.id.user_weight);
        user_nickname = (EditText) findViewById(R.id.user_nickname);
        add_user_finish = (Button) findViewById(R.id.add_user_finish);
        woman_radiobutton = (RadioButton) findViewById(R.id.woman_radiobutton);
        man_radiobutton = (RadioButton) findViewById(R.id.man_radiobutton);
        gender_radiogroup = (RadioGroup) findViewById(R.id.gender_radiogroup);
    }

    // TODO : 데이터 전송시 파라미터 명과 파라미터에 데이터 보낼 수 있음
    // TODO : http://server.name.com?data=senddata 형식임
    // TODO : onSuccess 및 onFailure는 콜백 리스너로 데이터를 request 후 response관련된 것 임
    // TODO : 회원가입 데이터 post 방식 전송 메소드

    public void postData(String url, UserDTO sendData){
        final RequestParams params = new RequestParams();
        params.put("name", sendData.getName());
        params.put("nickname", sendData.getNickname());
        params.put("birth", sendData.getBirth());
        params.put("gender", sendData.getGender());
        params.put("weight", sendData.getWeight());
        params.put("height", sendData.getHeight());
        params.put("photo", sendData.getPhoto());

        // url 및 전송된 데이터 확인 테스트
        Log.d(TAG, "url : " + url + "\nsendData : " + sendData.getNickname());

        HttpClientHelper.post(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 201){
                    Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다", Toast.LENGTH_SHORT).show();
                }else{
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
    public void onClick(View v) {

        // 입력한 데이터 저장
        String name, nickname, birth;
        int height, weight;

        name = user_name.getText().toString();
        nickname = user_nickname.getText().toString();
        birth = user_birthday.getText().toString();
        height = Integer.parseInt(user_height.getText().toString());
        weight = Integer.parseInt(user_weight.getText().toString());

        if(name.equals(" ") || nickname.equals(" ") || birth.equals(" ") || height == 0 || weight == 0){
            Toast.makeText(getApplicationContext(), "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
        }else{
            userDTO.setName(name);
            userDTO.setNickname(nickname);
            userDTO.setBirth(birth);
            userDTO.setHeight(height);
            userDTO.setWeight(weight);
            userDTO.setPhoto("photo.jpg");
            // url과 데이터 전송
            postData("http://14.63.213.62:3000/usercreate", userDTO);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}
