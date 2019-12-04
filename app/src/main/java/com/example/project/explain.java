package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class explain extends AppCompatActivity{
    private String name;
    public String urlto;
    public ViewPager viewPager;
    TextView StoreName, Explain, Phone;
    public MapView mapView;
    public ViewGroup mapViewContainer;

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
        mapView = new MapView(this);
        //mapView.setMapViewEventListener(this);
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        StoreName.setText(name);

        NetworkTask1 networkTask = new NetworkTask1(urlto,this,Phone,Explain,viewPager,this,mapView,mapViewContainer,name);
        networkTask.execute();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        mapView = new MapView(this);
        //mapView.setMapViewEventListener(this);
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        //mapViewContainer.addView(mapView);

        SharedPreferences prefs = getSharedPreferences("gpsData",0);
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(prefs.getString("cordinateX","")), Double.parseDouble(prefs.getString("cordinateY",""))), 1,true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(prefs.getString("name",""));
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(prefs.getString("cordinateX","")), Double.parseDouble(prefs.getString("cordinateY",""))));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);

        mapViewContainer.addView(mapView);
    }

}
