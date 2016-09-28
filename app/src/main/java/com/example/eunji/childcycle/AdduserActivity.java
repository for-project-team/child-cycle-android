package com.example.eunji.childcycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Eunji on 2016. 9. 12..
 */
public class AdduserActivity extends AppCompatActivity implements View.OnClickListener {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        setTitle("사용자 등록");


        EditText user_birthday = (EditText) findViewById(R.id.user_birthday);
        EditText user_height = (EditText) findViewById(R.id.user_height);
        EditText user_weight = (EditText) findViewById(R.id.user_weight);

        Button add_user_finish = (Button) findViewById(R.id.add_user_finish);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
