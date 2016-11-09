package com.example.eunji.childcycle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Eunji on 2016. 11. 9..
 */

public class FinishRidingFragment extends Fragment {

    private View v;
    private Fragment fragment;

    private Button total_history;
    private TextView riding_distance, riding_time;

    private void _InitUi(){

        total_history = (Button) v.findViewById(R.id.total_history);

        riding_distance = (TextView) v.findViewById(R.id.riding_distance);
        riding_time = (TextView) v.findViewById(R.id.riding_time);



    }

    public FinishRidingFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_finishriding, container, false);

        _InitUi();

        total_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                fragment = new RecordTableActivity();
//
//                if(fragment != null){
//                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                    ft.replace(R.id.content_frame, fragment);
//                    ft.commit();
//                }
            }
        });

        return v;
    }

}
