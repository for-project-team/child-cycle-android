package com.example.eunji.childcycle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;

/**
 * Created by Eunji on 2016. 10. 9..
 */

public class FinishRidingActivity extends AppCompatActivity{

    private void _InitUi(){

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finishriding);

        _InitUi();

        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(0xFFFFFFFF));
        actionBar.setTitle(Html.fromHtml("<font color='#000000'> ChildCycle </font>"));

    }
}
