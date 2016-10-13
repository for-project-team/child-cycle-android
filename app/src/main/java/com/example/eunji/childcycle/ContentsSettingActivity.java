package com.example.eunji.childcycle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 10. 12..
 */

public class ContentsSettingActivity extends ActionBarActivity {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contents_setting);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));



        //list 생성

        ListView setting_listview = (ListView)findViewById(R.id.setting_listview);

        ArrayList<ListviewItem> data = new ArrayList<>();

        ListviewItem user_change = new ListviewItem(R.drawable.ic_menu_camera,"사용자 변경");
        ListviewItem user_edit = new ListviewItem(R.drawable.ic_menu_camera,"사용자 수정");
        ListviewItem user_delete = new ListviewItem(R.drawable.ic_menu_camera,"사용자 삭제");

        data.add(user_change);
        data.add(user_edit);
        data.add(user_delete);

        ListviewAdapter adapter = new ListviewAdapter(this,R.layout.ui_setting_list,data);
        setting_listview.setAdapter(adapter);
    }
}
