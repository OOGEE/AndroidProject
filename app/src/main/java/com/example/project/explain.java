package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class explain extends AppCompatActivity implements MapView.MapViewEventListener {
    private String name;
    public String urlto;
    public ViewPager viewPager;
    TextView StoreName, Explain, Phone;
    public MapView mapView;
    public ViewGroup mapViewContainer;
    public String[] location = new String[2];
    ScrollView scrollView;

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
        scrollView = findViewById(R.id.explainScroll);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.scrollTo(0,0);
            }
        });
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
        mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        scrollView = findViewById(R.id.explainScroll);
        scrollView.scrollTo(0,0);

        SharedPreferences prefs = getSharedPreferences("gpsData",0);
        name = prefs.getString("name","");
        location[0] = prefs.getString("cordinateX","");
        location[1] = prefs.getString("cordinateY","");

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(location[0]), Double.parseDouble(location[1])), 1,true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(location[0]), Double.parseDouble(location[1])));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.addPOIItem(marker);
        mapView.setMapViewEventListener(this);

        mapViewContainer.addView(mapView);
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        mapViewContainer.removeAllViews();
        SharedPreferences prefs = getSharedPreferences("gpsData",0);
        Intent intent = new Intent(this,mapActivity.class);
        intent.putExtra("name",prefs.getString("name",""));
        intent.putExtra("cordinateX",prefs.getString("cordinateX",""));
        intent.putExtra("cordinateY",prefs.getString("cordinateY",""));
        startActivity(intent);
    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }
}
