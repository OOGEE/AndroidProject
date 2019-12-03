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
    public explainViewPagerAdapter(Context context, ArrayList<Bitmap> imageCache,String call,String explain,TextView Call,TextView Explain)
    {
        this.mContext = context;
        this.imageCache = imageCache;
        this.call = call;
        this.explain = explain;
        this.Call = Call;
        this.Explain = Explain;
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
