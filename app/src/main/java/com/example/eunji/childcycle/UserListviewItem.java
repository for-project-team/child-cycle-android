package com.example.eunji.childcycle;

/**
 * Created by Eunji on 2016. 11. 7..
 */

public class UserListviewItem {
    private int icon;
    private String name;
    private int add;

    public int getIcon(){
        return icon;
    }

    public String getName(){
        return name;
    }

    public int getAdd() { return add; }

    public UserListviewItem(int icon, String name, int add){
        this.icon = icon;
        this.name = name;
        this.add = add;
    }
}
