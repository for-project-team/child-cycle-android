package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 9. 11..
 */

public class PrepareFirstActivity extends Activity{
    int i=0;

    String s[] = {"보호장구를 착용하세요", "자전거에 탑승하세요", "hihi!"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_first);

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView txtview1 = (TextView) findViewById(R.id.text_change);

                if((i%3)==0)
                    txtview1.setText(s[0]);
                else if((i%3)==1)
                    txtview1.setText(s[1]);
                else if((i%3)==2) {
                    Intent intent = new Intent(getApplicationContext(), RidingMainActivity.class);
                    startActivity(intent);
                }

                i++;
            }
        });

    }

}
