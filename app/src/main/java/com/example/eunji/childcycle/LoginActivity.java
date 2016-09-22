package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 9. 12..
 */
public class LoginActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        findViewById(R.id.Adduser1).setOnClickListener(ClickListener);
        findViewById(R.id.Adduser2).setOnClickListener(ClickListener);
        findViewById(R.id.Adduser3).setOnClickListener(ClickListener);
        findViewById(R.id.Username1).setOnClickListener(Listener);
        findViewById(R.id.Username2).setOnClickListener(Listener);
        findViewById(R.id.Username3).setOnClickListener(Listener);
    }

    Button.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, AdduserActivity.class);
            startActivity(intent);
        }
    };

    TextView.OnClickListener Listener = new View.OnClickListener(){
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, PrepareFirstActivity.class);
            startActivity(intent);
        }
    };

}

