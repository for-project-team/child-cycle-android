package com.ccgirls.knu.childcycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ccgirls.knu.childcycle.dto.UserDTO;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 11. 9..
 */

public class UserSelectFragment extends Fragment implements View.OnClickListener {

    private View v;

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
    Fragment fragment;

    private ArrayList<UserDTO> userList;

    private Intent intent;


    private void _InitUi() {
        imgbtn1 = (ImageButton) v.findViewById(R.id.add_user);

        txtview1 = (TextView) v.findViewById(R.id.user_name);

        drawer = (DrawerLayout) v.findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) v.findViewById(R.id.nav_view);


        //list
        user_listview = (ListView) v.findViewById(R.id.user_listview);

        data = new ArrayList<>();

        adapter = new UserListviewAdapter(getActivity(), R.layout.ui_user_list, data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.user_select, container, false);

        _InitUi();

        userList = new ArrayList<>();

        save_string = new ArrayList<String>();

        user_listview.setAdapter(adapter);

        // 데이터 가져오기 완료 후 txtView에 출력
        new HttpTask(new OnCompletionListener() {
            @Override
            public void onComplete(ArrayList<UserDTO> result) {
                if (result != null) {
                    userList = result;
                    user1 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(0).getName(), R.mipmap.ic_user_add);
                    user2 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(1).getName(), R.mipmap.ic_user_add);
                    user3 = new UserListviewItem(R.mipmap.ic_user_profile, userList.get(2).getName(), R.mipmap.ic_user_add);
                    data.add(user1);
                    data.add(user2);
                    data.add(user3);
                    adapter.notifyDataSetChanged(); // 리스트뷰 갱신
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "서버가 연결되지 않았습니다", Toast.LENGTH_SHORT).show();
                }
            }
        }).execute();



        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_name:
                new AlertDialog.Builder(getActivity())
                        .setTitle("안전한 자전거를 시작합니다.")
                        .setMessage(user1.getName() + "이 맞습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // put Extra
                                intent = new Intent(getActivity().getApplicationContext(), PrepareActivity.class);
                                intent.putExtra("nickname", userList.get(0).getNickname());
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
                break;
            case R.id.add_user:
                Intent intent = new Intent(getActivity().getApplicationContext(), AdduserActivity.class);
                startActivity(intent);
                break;

        }
    }
}
/*
interface OnCompletionListener {
    void onComplete(ArrayList<UserDTO> result);
}

// 비동기통신 콜백함수
class HttpTask extends AsyncTask<ArrayList<UserDTO>, Void, ArrayList<UserDTO>> {

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

        if (params != null) {
            list = userInfoDAO.lodingUserData(getDataUrl);
           // userInfoDAO.findByNickname(getDataUrl, list.get(0).getNickname());
//            Log.d("Hanium", "MainActivity " + list.get(0).getNickname());
            return list;
        } else {
            return null;
        }
    }

    // 결과에 대해 호출되는 부분
    protected void onPostExecute(ArrayList<UserDTO> result) {
        // result : 웹서버로부터 가져온 값
        // 리턴받은 String데이터를 EditText에 출력
        if (listener != null)
            listener.onComplete(result);    // 리스너 호출
    }
}
*/
