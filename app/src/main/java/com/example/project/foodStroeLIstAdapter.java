package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class foodStroeLIstAdapter extends BaseAdapter {

    private Context ctx;
    private ArrayList<foodStoreListData> data;

    public foodStroeLIstAdapter(Context ctx, ArrayList<foodStoreListData> data){
        this.ctx = ctx;
        this.data = data;
    }

    @Override
    public int getCount(){
        return data.size();
    }

    @Override
    public Object getItem(int i){
        return i;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.food_list,viewGroup,false);
        }

        ImageView image = (ImageView) view.findViewById(R.id.foodStoreImage);
        image.setImageBitmap(data.get(i).icon);
        TextView text = (TextView) view.findViewById(R.id.foodStoreText);
        text.setText(data.get(i).name);

        return view;
    }

}
