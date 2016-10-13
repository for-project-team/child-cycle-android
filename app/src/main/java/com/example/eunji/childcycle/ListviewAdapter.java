package com.example.eunji.childcycle;

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

public class ListviewAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<ListviewItem> data;
    private int layout;
    private ImageView icon;
    private TextView name;

    public ListviewAdapter(Context context, int layout, ArrayList<ListviewItem> data){
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

        ListviewItem listviewItem = data.get(position);

        icon = (ImageView) convertView.findViewById(R.id.list_imageview);
        icon.setImageResource(listviewItem.getIcon());

        name = (TextView) convertView.findViewById(R.id.list_text);
        name.setText(listviewItem.getName());

        return convertView;
    }
}