package com.ccgirls.knu.childcycle.item;

/**
 * Created by Eunji on 2016. 10. 13..
 */

public class ListviewItem {
    private int icon;
    private String name;

    public int getIcon(){
        return icon;
    }

    public String getName(){
        return name;
    }

    public ListviewItem(int icon, String name){
        this.icon = icon;
        this.name = name;
    }
}
