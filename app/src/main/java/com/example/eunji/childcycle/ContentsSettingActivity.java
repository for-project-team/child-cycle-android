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
    private ListviewItem user_edit;
    private ListviewItem user_delete;
    private ListviewAdapter adapter;
    private TextView list_Text;
    private String list_text;

    private void _InitUi() {

        setting_listview = (ListView) findViewById(R.id.setting_listview);

        data = new ArrayList<>();

        user_edit = new ListviewItem(R.drawable.ic_menu_camera, "사용자 수정");
        user_delete = new ListviewItem(R.drawable.ic_menu_camera, "사용자 삭제");

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

        data.add(user_edit);
        data.add(user_delete);

        setting_listview.setAdapter(adapter);
        setting_listview.setOnItemClickListener(new ListClickHandler());
    }

    public class ListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            // TODO Auto-generated method stub
            list_Text = (TextView) view.findViewById(R.id.list_text);
            list_text = list_Text.getText().toString();

            if (list_text.equals("사용자 수정")) {

                actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFF5722));
                actionBar.setTitle(Html.fromHtml("<font color='#ffffff'> ChildCycle </font>"));

                setContentView(R.layout.activity_adduser);
            } else if (list_text.equals("사용자 삭제"))
                System.out.println("delete");

        }

    }
}
