package com.example.eunji.childcycle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 9. 11..
 */

public class PrepareActivity extends Activity {
    public static Button btn;
    TextView txtview1;
    ImageView imgview;
    ImageButton imgbtn[] = new ImageButton[4];
    Drawable imgbtn_toggle[] = new Drawable[2];

    String s[] = {"헬멧을 착용하세요", "보호장구를 착용하세요", "자전거에 탑승하세요", "hihi!"};
    String s1[] = {"착용완료", "확인완료"};

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare);

        txtview1 = (TextView) findViewById(R.id.text_change);
        imgview = (ImageView) findViewById(R.id.prepare_image);

        btn = (Button) findViewById(R.id.button);

        imgbtn[0] = (ImageButton) findViewById(R.id.action_button1);
        imgbtn[1] = (ImageButton) findViewById(R.id.action_button2);
        imgbtn[2] = (ImageButton) findViewById(R.id.action_button3);
        imgbtn[3] = (ImageButton) findViewById(R.id.action_button4);

        imgbtn_toggle[0] = getResources().getDrawable(R.drawable.ic_menu_camera);
        imgbtn_toggle[1] = getResources().getDrawable(R.drawable.ic_menu_gallery);


//        for ( i = 0; i < 4; i++) {
//            imgbtn[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    for(int i = 0; i < 4; i++){
//                    for (int j = 0; j < 4; j++) {
//                        if (i == j)
//                            imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
//                        else
//                            imgbtn[j].setImageDrawable(imgbtn_toggle[1]);
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

        imgbtn[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtn[0].setImageDrawable(imgbtn_toggle[0]);

                for (int i = 1; i < 4; i++)
                    imgbtn[i].setImageDrawable(imgbtn_toggle[1]);

                txtview1.setText(s[0]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_camera);
            }
        });

        imgbtn[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++) {
                    if (i == 1)
                        imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
                    else
                        imgbtn[i].setImageDrawable(imgbtn_toggle[1]);
                }

                txtview1.setText(s[1]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_gallery);
            }
        });

        imgbtn[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 4; i++) {
                    if (i == 2)
                        imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
                    else
                        imgbtn[i].setImageDrawable(imgbtn_toggle[1]);
                }

                txtview1.setText(s[2]);
                btn.setText(s1[0]);
                imgview.setImageResource(R.drawable.ic_menu_manage);
            }
        });

        imgbtn[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgbtn[3].setImageDrawable(imgbtn_toggle[0]);

                for (int i = 0; i < 3; i++)
                    imgbtn[i].setImageDrawable(imgbtn_toggle[1]);

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
                            imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
                        else
                            imgbtn[i].setImageDrawable(imgbtn_toggle[1]);
                    }

                        txtview1.setText(s[1]);
                        btn.setText(s1[0]);
                }

                else if (s[1].equals(text)) {
                    for(int i=0; i<4; i++) {
                        if(i == 2)
                            imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
                        else
                            imgbtn[i].setImageDrawable(imgbtn_toggle[1]);
                    }

                    txtview1.setText(s[2]);
                    btn.setText(s1[0]);
                }

                else if (s[2].equals(text)) {
                    for(int i=0; i<4; i++) {
                        if(i == 3)
                            imgbtn[i].setImageDrawable(imgbtn_toggle[0]);
                        else
                            imgbtn[i].setImageDrawable(imgbtn_toggle[1]);
                    }

                    txtview1.setText(s[2]);
                    btn.setText(s1[1]);
                }

            }
        });

    }
}
