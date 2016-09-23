package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eunji on 2016. 9. 11..
 */
public class PrepareFirstActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_first);

        findViewById(R.id.button).setOnClickListener(ClickListener);
    }

    Button.OnClickListener ClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(PrepareFirstActivity.this, PrepareSecondActivity.class);
            startActivity(intent);
        }
    };

}
