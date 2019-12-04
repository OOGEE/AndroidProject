package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class explainViewPagerAdapter extends PagerAdapter {
    Handler handler = new Handler();
    private Context mContext;
    private ArrayList<Bitmap> imageCache;
    private String call;
    private String explain;
    private TextView Call,Explain;
    private String[] location;
    private ViewGroup mapViewContainer;
    private MapView mapView;

    public explainViewPagerAdapter(Context context, ArrayList<Bitmap> imageCache,String call,String explain,TextView Call,TextView Explain,String[] location)
    {
        this.mContext = context;
        this.imageCache = imageCache;
        this.call = call;
        this.explain = explain;
        this.Call = Call;
        this.Explain = Explain;
        this.location = location;
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

        mapView = new MapView(mContext);

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(Double.parseDouble(location[0]), Double.parseDouble(location[1])), true);


        // 줌 레벨 변경
        mapView.setZoomLevel(3, true);

        mapViewContainer = (ViewGroup) view.findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);


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
}
