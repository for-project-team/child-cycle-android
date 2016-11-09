package com.example.eunji.childcycle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Eunji on 2016. 9. 11..
 */
public class PrepareFourthActivity extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    private View v;

    private Button button;
    private Fragment fragment;
    private Intent intent;

    private Toolbar toolbar;

    private DrawerLayout drawer;


    public PrepareFourthActivity(){

    }

    private void _InitUi() {
        button = (Button) v.findViewById(R.id.button);
        drawer = (DrawerLayout) v.findViewById(R.id.drawer);
        toolbar = (Toolbar) v.findViewById(R.id.app_toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_prepare_fourth, container, false);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        _InitUi();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) v.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        toolbar.setTitle(" ChildCycle");
//        toolbar.setLogo(R.mipmap.hamburger);
        toolbar.setTitleTextColor(Color.BLACK);
        toolbar.setBackgroundColor(Color.WHITE);
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
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
// Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.drawer_main) {

            Intent intent1 = new Intent(getContext(), RidingMainActivity.class);
            startActivity(intent1);
//            fragment = new RidingFragment();
//
//            if(fragment != null){
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.content_frame, fragment);
//                ft.commit();
//            }

        } else if (id == R.id.drawer_history) {
            Intent intent1 = new Intent(getContext(), RecordTableActivity.class);
            startActivity(intent1);
        } else if (id == R.id.drawer_setting) {
            Intent intent2 = new Intent(getContext(), ContentsSettingActivity.class);
            startActivity(intent2);
        }

        this.drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}