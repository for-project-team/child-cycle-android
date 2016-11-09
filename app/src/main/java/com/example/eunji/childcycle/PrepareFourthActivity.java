package com.example.eunji.childcycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Eunji on 2016. 9. 11..
 */
public class PrepareFourthActivity extends Fragment {

    private View v;

    private Button button;
    private Fragment fragment;
    private Intent intent;

    public PrepareFourthActivity(){

    }

    private void _InitUi() {
        button = (Button) v.findViewById(R.id.button);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_prepare_fourth, container, false);

        _InitUi();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment = new RidingFragment();
//
//                if(fragment != null){
//                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.content_frame, fragment);
//                    ft.commit();
//                }

                intent = new Intent(getActivity().getApplicationContext(), RidingMainActivity.class);
            }
        });

        return v;
    }

}