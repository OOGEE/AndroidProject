package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class explain extends AppCompatActivity {
    private String name;
    public String urlto;
    public ViewPager viewPager;
    TextView StoreName, Explain, Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        urlto = "http://toojs.asuscomm.com:8643/data/speciefStoreData/" ;
        try {
            urlto += URLEncoder.encode(name,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StoreName = findViewById(R.id.StoreName);
        Explain = findViewById(R.id.Explain);
        Phone = findViewById(R.id.Phone);
        viewPager = findViewById(R.id.explainView);

        StoreName.setText(name);

        NetworkTask1 networkTask = new NetworkTask1(urlto,this,Phone,Explain,viewPager,this);
        networkTask.execute();
    }

}
