package com.example.eunji.childcycle.urlconnection;

import android.os.Looper;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

/**
 * Created by choihyesun on 2016. 10. 10..
 */

// TODO : 기본적인 구조는 restful api를 만들기 위해 get post delete put 헤더를 보낼 수 있으며
// TODO : 파라미터 뿐만아니라 Object, File 여러가지 형식을 보낼 수 있음 또한 헤더 추가가능
// TODO : 기본적으로 내부에 스레드로 전송하기 때문에 따로 구현 필요없음. 그대로 사용하면됨

public class HttpClientHelper {

    public static AsyncHttpClient client = new AsyncHttpClient();
    public static AsyncHttpClient syncHttpClient= new SyncHttpClient();
    public static PersistentCookieStore myCookieStore;

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().setMaxRetriesAndTimeout(1, 5000);
        getClient().setTimeout(5000);
        getClient().get(url, params, responseHandler);
    }
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().setMaxRetriesAndTimeout(1, 5000);
        getClient().setTimeout(5000);
        getClient().post(url, params, responseHandler);
    }
    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().setMaxRetriesAndTimeout(1, 5000);
        getClient().setTimeout(5000);
        getClient().put(url, params, responseHandler);
    }
    public static void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().setMaxRetriesAndTimeout(1, 5000);
        getClient().setTimeout(5000);
        getClient().delete(url, params, responseHandler);
    }

    public static void getV116(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().setMaxRetriesAndTimeout(1, 5000);
        getClient().setTimeout(5000);
        getClient().get(url, params, responseHandler);
    }


    private static AsyncHttpClient getClient()
    {
        // Return the synchronous HTTP client when the thread is not prepared
        if (Looper.myLooper() == null)
            return syncHttpClient;
        return client;
    }
}
