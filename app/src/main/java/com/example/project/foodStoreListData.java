package com.example.project;

import android.graphics.Bitmap;

public class foodStoreListData {

    public Bitmap icon;
    public String name;

    public foodStoreListData(Bitmap icon, String name){
        this.icon = icon;
        this.name = name;
    }

    public void addBitmapData(Bitmap icon){
        this.icon = icon;
    }
}
