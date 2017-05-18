package com.ccgirls.knu.childcycle.fragment;

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

import com.ccgirls.knu.childcycle.R;
import com.ccgirls.knu.childcycle.item.UserListviewItem;
import com.ccgirls.knu.childcycle.activity.AdduserActivity;
import com.ccgirls.knu.childcycle.activity.MainActivity;
import com.ccgirls.knu.childcycle.activity.PrepareActivity;
import com.ccgirls.knu.childcycle.adapter.UserListviewAdapter;
import com.ccgirls.knu.childcycle.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 11. 9..
 */

public class UserSelectFragment extends Fragment implements View.OnClickListener {

    private View v;

    private ListView user_listview;
    private ArrayList<UserListviewItem> data;
    private UserListviewItem user1, user2, user3, user4;
    private UserListviewAdapter adapter;
    private ArrayList<String> save_string;
    private TextView list_Text;
    private String list_text;

    private ImageButton imgbtn1;
    private TextView txtview1;

    private DrawerLayout drawer;
    private NavigationView navigationView;
    Fragment fragment;
    private MainActivity mainActivity;

    private ArrayList<UserVO> userList;

    private Intent intent;


    private void initUi() {
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

        initUi();

        userList = new ArrayList<>();
        save_string = new ArrayList<String>();
        user_listview.setAdapter(adapter);

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

