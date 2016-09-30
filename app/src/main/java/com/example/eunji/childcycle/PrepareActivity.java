package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 9. 11..
 */

public class PrepareActivity extends Activity {
    public static Button btn;
    TextView txtview1;
    ImageView imgview;
    Button strkbtn[] = new Button[4];
    Drawable strkbtn_toggle[] = new Drawable[2];

    String s[] = {"헬멧을 착용하세요", "보호장구를 착용하세요", "자전거에 탑승하세요", "hihi!"};
    String s1[] = {"착용완료", "확인완료"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        txtview1 = (TextView) findViewById(R.id.text_change);
        imgview = (ImageView) findViewById(R.id.prepare_image);

        btn = (Button) findViewById(R.id.button);

        strkbtn[0] = (Button) findViewById(R.id.stroke1);
        strkbtn[1] = (Button) findViewById(R.id.stroke2);
        strkbtn[2] = (Button) findViewById(R.id.stroke3);
        strkbtn[3] = (Button) findViewById(R.id.stroke4);

        strkbtn_toggle[0] = getResources().getDrawable(R.drawable.stroke_button_clicked);
        strkbtn_toggle[1] = getResources().getDrawable(R.drawable.stroke_button);


//        for ( i = 0; i < 4; i++) {
//            strkbtn[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for(int i = 0; i < 4; i++){
//                    for (int j = 0; j < 4; j++) {
//                        if (i == j)
//                            strkbtn[i].setImageDrawable(strkbtn_toggle[0]);
//                        else
//                            strkbtn[j].setImageDrawable(strkbtn_toggle[1]);
//                    }

//                        txtview1.setText(s[i]);
//
//                        if (i != 3)
//                            btn.setText(s1[0]);
//                        else
//                            btn.setText(s1[1]);
//                    }
//
//
//                }
//            });
//        }
//    }

        strkbtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strkbtn[0].setBackground(strkbtn_toggle[0]);

                for (int i = 1; i < 4; i++)
                    strkbtn[i].setBackground(strkbtn_toggle[1]);

                txtview1.setText(s[0]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_camera);
            }
        });

        strkbtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++) {
                    if (i == 1)
                        strkbtn[i].setBackground(strkbtn_toggle[0]);
                    else
                        strkbtn[i].setBackground(strkbtn_toggle[1]);
                }

                txtview1.setText(s[1]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_gallery);
            }
        });

        strkbtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++) {
                    if (i == 2)
                        strkbtn[i].setBackground(strkbtn_toggle[0]);
                    else
                        strkbtn[i].setBackground(strkbtn_toggle[1]);
                }

                txtview1.setText(s[2]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_manage);
            }
        });

        strkbtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strkbtn[3].setBackground(strkbtn_toggle[0]);

                for (int i = 0; i < 3; i++)
                    strkbtn[i].setBackground(strkbtn_toggle[1]);

                txtview1.setText(s[3]);
                btn.setText(s1[1]);
                imgview.setImageResource(R.drawable.ic_menu_share);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = btn.getText().toString();
                String text = txtview1.getText().toString();

                if(s1[1].equals(str)) {
                    Intent intent = new Intent(getApplicationContext(), RidingMainActivity.class);
                    startActivity(intent);
                }

                if (s[0].equals(text)) {
                    for(int i=0; i<4; i++) {
                        if(i == 1)
                            strkbtn[i].setBackground(strkbtn_toggle[0]);
                        else
                            strkbtn[i].setBackground(strkbtn_toggle[1]);
                    }

                        txtview1.setText(s[1]);
                        btn.setText(s1[0]);
                }

                else if (s[1].equals(text)) {
                    for(int i=0; i<4; i++) {
                        if(i == 2)
                            strkbtn[i].setBackground(strkbtn_toggle[0]);
                        else
                            strkbtn[i].setBackground(strkbtn_toggle[1]);
                    }

                    txtview1.setText(s[2]);
                    btn.setText(s1[0]);
                }

                else if (s[2].equals(text)) {
                    for(int i=0; i<4; i++) {
                        if(i == 3)
                            strkbtn[i].setBackground(strkbtn_toggle[0]);
                        else
                            strkbtn[i].setBackground(strkbtn_toggle[1]);
                    }

                    txtview1.setText(s[2]);
                    btn.setText(s1[1]);
                }

            }
        });

    }
}
