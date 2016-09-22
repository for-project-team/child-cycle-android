package com.example.eunji.childcycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * Created by Eunji on 2016. 9. 12..
 */
public class LoginActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);


        Button btn = (Button) findViewById(R.id.adduser1);

//        findViewById(R.id.Adduser1).setOnClickListener(ClickListener);
//        findViewById(R.id.Adduser2).setOnClickListener(ClickListener);
//        findViewById(R.id.Adduser3).setOnClickListener(ClickListener);
//        findViewById(R.id.Username1).setOnClickListener(Listener);
//        findViewById(R.id.Username2).setOnClickListener(Listener);
//        findViewById(R.id.Username3).setOnClickListener(Listener);


        btn.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {

                System.out.print("클릭");
                Intent intent = new Intent(getApplicationContext(), AdduserActivity.class);
                startActivity(intent);
            }
        });


//        Button.OnClickListener ClickListener = new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), AdduserActivity.class);
//                startActivity(intent);
//            }
//        };
//
//        TextView.OnClickListener Listener = new View.OnClickListener(){
//            public void onClick(View v) {
//                Intent intent = new Intent(LoginActivity.this, PrepareFirstActivity.class);
//                startActivity(intent);
//            }
//        };

    }
}

