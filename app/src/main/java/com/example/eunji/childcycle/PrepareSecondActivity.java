package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eunji on 2016. 9. 11..
 */
public class PrepareSecondActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_second);

        findViewById(R.id.button).setOnClickListener(ClickListener);
    }

    Button.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(PrepareSecondActivity.this, PrepareThirdActivity.class);
            startActivity(intent);
        }
    };

}