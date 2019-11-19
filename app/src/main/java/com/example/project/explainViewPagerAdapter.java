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

import androidx.viewpager.widget.PagerAdapter;

import java.io.InputStream;
import java.net.URL;

public class explainViewPagerAdapter extends PagerAdapter {
    Handler handler = new Handler();
    private Context mContext;
    private String [] urlStringArray ;
    public explainViewPagerAdapter(Context context, String urlStringArray)
    {
        this.mContext = context;
        this.urlStringArray = urlStringArray.split(",");
        Log.d("pager",urlStringArray);
    }

    @Override
    public int getCount() {
        return urlStringArray.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.explainimageviewpage, null);

        ImageView imageView = view.findViewById(R.id.explainImageView);
        getInternetImage(urlStringArray[position],imageView);


        container.addView(view);
        Log.d("pager",position+"");

        return view;
    }
    public void getInternetImage(final String urlSource,final ImageView imageview){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;

                    URL url = new URL(urlSource);

                    InputStream is = url.openStream();

                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            imageview.setImageBitmap(bm);
                        }
                    });
                    //imageview.setImageBitmap(bm); //비트맵 객체로 보여주기
                    //Log.d("image","image get succes : " + urlSource);
                } catch(Exception e){

                }

            }
        });

        t.start();

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
