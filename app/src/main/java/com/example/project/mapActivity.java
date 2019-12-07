package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.ArrayList;

public class mapActivity extends AppCompatActivity {

    ViewGroup mapViewContainer;
    String name;
    String cordinateX;
    String cordinateY;
    String call;
    String explain;
    ArrayList<Bitmap> imageCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapView mapView = new MapView(this);

        mapViewContainer = (ViewGroup) findViewById(R.id.activity_mapView);
        mapViewContainer.addView(mapView);
        name = getIntent().getStringExtra("name");
        cordinateX = getIntent().getStringExtra("cordinateX");
        cordinateY = getIntent().getStringExtra("cordinateY");
        imageCache = (ArrayList<Bitmap>)getIntent().getSerializableExtra("images");
        call = getIntent().getStringExtra("call");
        explain = getIntent().getStringExtra("expalin");

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(cordinateX), Double.parseDouble(cordinateY)), 1,true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(cordinateX), Double.parseDouble(cordinateY)));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
        Log.d("test",getIntent().getStringExtra("name"));
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapViewContainer.removeAllViews();
        SharedPreferences prefs = getSharedPreferences("gpsData",0);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name",name);
        editor.putString("cordinateX",cordinateX);
        editor.putString("cordinateY",cordinateY);
        editor.apply();
    }
}
