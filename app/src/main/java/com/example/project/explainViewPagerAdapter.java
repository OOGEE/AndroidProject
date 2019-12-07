package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;
import java.util.ArrayList;

public class explainViewPagerAdapter extends PagerAdapter implements MapView.MapViewEventListener{
    private Context mContext;
    private ArrayList<Bitmap> imageCache;
    private String call;
    private String explain;
    private TextView Call,Explain;
    private String[] location;
    private String name;
    public ViewGroup mapViewContainer;
    public MapView mapView;

    public explainViewPagerAdapter(Context context, ArrayList<Bitmap> imageCache,String call,String explain,TextView Call,TextView Explain,String[] location,MapView mapView,ViewGroup mapViewContainer,String name)
    {
        this.mContext = context;
        this.imageCache = imageCache;
        this.call = call;
        this.explain = explain;
        this.Call = Call;
        this.Explain = Explain;
        this.location = location;
        this.mapView = mapView;
        this.mapViewContainer = mapViewContainer;
        this.name = name;
        Call.setText(call);
        Explain.setText(explain);
    }

    @Override
    public int getCount() {
        return imageCache.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.explainimageviewpage, null);

        ImageView imageView = view.findViewById(R.id.explainImageView);
        imageView.setImageBitmap(imageCache.get(position));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(Double.parseDouble(location[0]), Double.parseDouble(location[1])), 1,true);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName(name);
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(location[0]), Double.parseDouble(location[1])));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        mapView.setMapViewEventListener(this);
        mapView.addPOIItem(marker);

        container.addView(view);

        return view;
    }

    @Override
    public boolean isViewFromObject(View view,Object o) {
        return (view == (View)o);
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


    @Override
    public void onMapViewInitialized(MapView mapView) {

    }

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {

    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
        mapViewContainer.removeAllViews();
        Intent intent = new Intent(mContext,mapActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("cordinateX",location[0]);
        intent.putExtra("cordinateY",location[1]);
        mContext.startActivity(intent);
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
