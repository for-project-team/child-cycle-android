package com.example.eunji.childcycle;

/**
 * Created by Eunji on 2016. 11. 7..
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Eunji on 2016. 10. 13..
 */

public class UserListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<UserListviewItem> data;
    private int layout;
    private ImageView icon;
    private TextView name;
    private ImageView add;

    public UserListviewAdapter(Context context, int layout, ArrayList<UserListviewItem> data){
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
        this.layout = layout;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public String getItem(int position){
        return data.get(position).getName();
    }


    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if ( convertView == null ){
            convertView = inflater.inflate(layout,parent,false);
        }

        UserListviewItem userlistviewItem = data.get(position);

        icon = (ImageView) convertView.findViewById(R.id.list_imageview);
        icon.setImageResource(userlistviewItem.getIcon());

        name = (TextView) convertView.findViewById(R.id.list_text);
        name.setText(userlistviewItem.getName());

        add = (ImageView) convertView.findViewById(R.id.list_imageview);
        add.setImageResource(userlistviewItem.getAdd());

        return convertView;
    }

}
