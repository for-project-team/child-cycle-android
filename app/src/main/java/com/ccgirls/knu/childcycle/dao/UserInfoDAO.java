package com.ccgirls.knu.childcycle.dao;

import android.util.Log;

import com.ccgirls.knu.childcycle.vo.UserVO;
import com.ccgirls.knu.childcycle.urlconnection.HttpClientHelper;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by choihyesun on 2016. 10. 14..
 */

public class UserInfoDAO {
    private static final String TAG = "Hanium";

    public ArrayList<UserVO> list;

    // 메인화면 - 회원 이름 표시
    public ArrayList<UserVO> lodingUserData(String url){
        ArrayList<UserVO> uList = getData(url);
        return uList;
    }

    // 닉네임으로 회원정보 불러오기
    public void findByNickname (String url, String nickname){
        getData(url, nickname);
    }

    public ArrayList<UserVO> getData(String url){

        list = new ArrayList<UserVO>();

        RequestParams params = new RequestParams();
        HttpClientHelper.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    Log.i(TAG, "회원리스트 저장 완료");
                }else{
                    Log.i(TAG, "회원리스트 저장 실패");
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                try{
                    for(int i = 0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);

                        UserVO user = new UserVO();
                        user.setName(data.getString("name"));
                        user.setNickname(data.getString("nickname"));
                        user.setPhoto(data.getString("photo"));
                        user.setHeight(data.getInt("height"));
                        user.setWeight(data.getInt("weight"));
                        user.setBirth(data.getString("birth"));
                        user.setGender(data.getInt("gender"));
                        list.add(user);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                if(statusCode == 200){
                    Log.i(TAG, "회원리스트 저장 완료");
                }else{
                    Log.i(TAG, "회원리스트 저장 실패");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure statusCode : " + statusCode);
                Log.d(TAG, "throwable" + throwable + " errorResponse" + errorResponse);
            }
        });

        return list;
    }

    // nickname으로 회원정보 불러오기
    public ArrayList<UserVO> getData(String url, String nickname){

        list = new ArrayList<UserVO>();

        RequestParams params = new RequestParams();
        params.put("nickname", nickname);

        // url 및 전송된 데이터 확인 테스트
        Log.d(TAG, "url : " + url + "\nsendData : " + nickname);

        HttpClientHelper.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    Log.i(TAG, "회원리스트 불러오기 완료234");
                }else{
                    Log.i(TAG, "회원리스트 불러오기 실패234");
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                try{
                    for(int i = 0; i<response.length(); i++){
                        JSONObject data = response.getJSONObject(i);

                        UserVO user = new UserVO();
                        user.setName(data.getString("name"));
                        user.setNickname(data.getString("nickname"));
                        user.setPhoto(data.getString("photo"));
                        user.setHeight(data.getInt("height"));
                        user.setWeight(data.getInt("weight"));
                        user.setBirth(data.getString("birth"));
                        user.setGender(data.getInt("gender"));

                        list.add(user);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                if(statusCode == 200){
                    Log.i(TAG, "회원리스트 불러오기 완료");
                }else{
                    Log.i(TAG, "회원리스트 불러오기 실패");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d(TAG, "onFailure statusCode : " + statusCode);
                Log.d(TAG, "throwable" + throwable + " errorResponse" + errorResponse);
            }
        });

        return list;
    }
}
