package com.example.eunji.childcycle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 10. 12..
 */

public class ContentsSettingActivity extends ActionBarActivity {

    private ActionBar actionBar;
    private ListView setting_listview;
    private ArrayList<ListviewItem> data;
    private ArrayList<String> save_string;
    private ListviewItem user_edit, user_change, user_delete, user_logout, audio_onoff;
    private ListviewAdapter adapter;
    private TextView list_Text;
    private String list_text;

    private void _InitUi() {

        setting_listview = (ListView) findViewById(R.id.setting_listview);

        data = new ArrayList<>();

        user_change = new ListviewItem(R.drawable.ic_menu_camera, "사용자 변경");
        user_edit = new ListviewItem(R.drawable.ic_menu_camera, "프로필 수정");
        audio_onoff = new ListviewItem(R.drawable.ic_menu_camera, "음성안내");
        user_delete = new ListviewItem(R.drawable.ic_menu_camera, "사용자 삭제");
        user_logout = new ListviewItem(R.drawable.ic_menu_camera, "로그아웃");

        adapter = new ListviewAdapter(this, R.layout.ui_setting_list, data);

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_setting);

        _InitUi();

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

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

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            list_Text = (TextView) view.findViewById(R.id.list_text);
            list_text = list_Text.getText().toString();

            if(list_text.equals(user_change.getName())){
                actionBar.hide();

                setContentView(R.layout.content_main);
            }

            else if (list_text.equals(user_edit.getName())) {

                actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF5722));
                actionBar.setTitle(Html.fromHtml("<font color='#ffffff'> ChildCycle </font>"));

                setContentView(R.layout.activity_adduser);
            }

            else if (list_text.equals(user_delete.getName()))
                System.out.println("delete");

            else if(list_text.equals(user_logout.getName()))
                finish();

            else if(list_text.equals(audio_onoff.getName())) {
                finish();                                   // 수정요망 // 클릭되면 아이콘(이미지)를 on/off....
            }
        }

    }
}
