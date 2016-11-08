package com.example.eunji.childcycle;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eunji.childcycle.dao.UserInfoDAO;
import com.example.eunji.childcycle.dto.UserDTO;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView user_listview;
    private ArrayList<UserListviewItem> data;
    private UserListviewItem user1, user2, user3;
    private UserListviewAdapter adapter;
    private ArrayList<String> save_string;
    private TextView list_Text;
    private String list_text;

    private ImageButton imgbtn1;
    private TextView txtview1;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private ArrayList<UserDTO> userList;

    private Intent intent;

    private void _InitUi() {

        imgbtn1 = (ImageButton) findViewById(R.id.add_user);

        txtview1 = (TextView) findViewById(R.id.user_name);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        //list
        user_listview = (ListView) findViewById(R.id.setting_listview);

        data = new ArrayList<>();

        user1 = new UserListviewItem(R.mipmap.ic_user_profile, "사용자 추가", R.mipmap.ic_user_add);
        //System.out.print("debug : " + user1);
        user2 = new UserListviewItem(R.mipmap.ic_user_profile, "사용자 추가", R.mipmap.ic_user_add);
        user3 = new UserListviewItem(R.mipmap.ic_user_profile, "사용자 추가", R.mipmap.ic_user_add);

        adapter = new UserListviewAdapter(this, R.layout.ui_user_list, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);

        _InitUi();

        userList = new ArrayList<>();

        save_string = new ArrayList<String>();

        data.add(user1);
        data.add(user2);
        data.add(user3);

        user_listview.setAdapter(adapter);


        // 데이터 가져오기 완료 후 txtView에 출력
        new HttpTask(new OnCompletionListener() {
            @Override
            public void onComplete(ArrayList<UserDTO> result) {
                if(result != null) {
                    userList = result;
                    user1 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(0).getName(), R.mipmap.ic_user_add);
                    user2 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(1).getName(), R.mipmap.ic_user_add);
                    user3 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(2).getName(), R.mipmap.ic_user_add);
                    data.add(user1);
                    data.add(user2);
                    data.add(user3);
                    adapter.notifyDataSetChanged(); // 리스트뷰 갱신
                }else{
                    Toast.makeText(getApplicationContext(), "서버가 연결되지 않았습니다", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();

//        user_listview.setOnItemClickListener(clickListener);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    //회원가입 버튼 클릭 리스너
    public void btnClick(View v) {
        Intent intent = new Intent(getApplicationContext(), AdduserActivity.class);
        startActivity(intent);

    }

//    AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            final int itemPosition = position;
//            new AlertDialog.Builder(MainActivity.this)
//                    .setTitle("안전한 자전거를 시작합니다.")
//                    .setMessage(userList.get(itemPosition).getName() + "이 맞습니까?")
//                    .setPositiveButton("예", new DialogInterface.OnClickListener() {
//
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // put Extra
//                            intent = new Intent(getApplicationContext(), PrepareActivity.class);
//                            intent.putExtra("nickname", userList.get(itemPosition).getNickname());
//                            startActivity(intent);
//                        }
//                    })
//                    .setNegativeButton("아니오", null)
//                    .show();
//        }
//    };

    // textView 클릭 메소드
    public void txtClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("안전한 자전거를 시작합니다.")
                .setMessage(user1.getName() + "이 맞습니까?")
                .setPositiveButton("예", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // put Extra
                        intent = new Intent(getApplicationContext(), PrepareActivity.class);
                        intent.putExtra("nickname", userList.get(0).getNickname());
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
            Intent intent1 = new Intent(getApplicationContext(), RidingMainActivity.class);
            startActivity(intent1);
        }

        else if (id == R.id.drawer_history) {
            setContentView(R.layout.record_table);          // 수정필요
        }

        else if (id == R.id.drawer_setting) {
            Intent intent2 = new Intent(getApplicationContext(), ContentsSettingActivity.class);
            startActivity(intent2);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

// 리스너
interface OnCompletionListener {
    void onComplete(ArrayList<UserDTO> result);
}

// 비동기통신 콜백함수
class HttpTask extends AsyncTask<ArrayList<UserDTO> , Void , ArrayList<UserDTO>> {

    OnCompletionListener listener = null;
    UserInfoDAO userInfoDAO = new UserInfoDAO();
    ArrayList<UserDTO> list = new ArrayList<>();
    String getDataUrl = "http://14.63.213.62:3000/users";

    public HttpTask() {
    }

    public HttpTask(OnCompletionListener listener) {
        this.listener = listener;
    }

    protected ArrayList<UserDTO> doInBackground(ArrayList<UserDTO>... params) {

        if(params != null){
            list = userInfoDAO.lodingUserData(getDataUrl);
            userInfoDAO.findByNickname(getDataUrl, list.get(0).getNickname());
            Log.d("Hanium", list.get(0).getNickname());
            return list;
        }else{
            return null;
        }
    }

    // 결과에 대해 호출되는 부분
    protected void onPostExecute(ArrayList<UserDTO> result) {
        // result : 웹서버로부터 가져온 값
        // 리턴받은 String데이터를 EditText에 출력
        if ( listener != null)
            listener.onComplete(result);    // 리스너 호출
    }
}