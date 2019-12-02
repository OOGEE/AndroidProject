package com.example.project;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTask1 extends AsyncTask<Void, Void, String> {

    private String url;
    private Context context;
    public ViewPager viewPager;
    public TextView Phone,Explain;
    public ArrayList<Bitmap> imageCache = new ArrayList<Bitmap>();
    public Context ctx;

    ProgressDialog asyncDialog;

    public NetworkTask1(String url, Context context, TextView Phone,TextView Explain,ViewPager viewPager,Context ctx) {
        this.context = context;
        this.url = url;
        this.Phone = Phone;
        this.Explain = Explain;
        this.ctx = ctx;
        this.viewPager = viewPager;
        asyncDialog  = new ProgressDialog(ctx);
    }

    @Override
    protected void onPreExecute() {
        asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        asyncDialog.setMessage("로딩중입니다..");
        // show dialog
        asyncDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... params) {
        RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
        String result = requestHttpURLConnection.request(url, null); // 해당 URL로 부터 결과물을 얻어온다.
        try {
            JSONObject jsonObjects = new JSONObject(result.substring(1, result.length()));
            Phone.setText("전화 : " + jsonObjects.getString("call"));
            Explain.setText(jsonObjects.getString("text"));
            String [] urlStringArray = (jsonObjects.getString("mainphotourl")+","+jsonObjects.getString("subphotourl")).split(",");
            for(int i = 0;i<urlStringArray.length;i++){
                URL url = new URL(urlStringArray[i]);
                InputStream is = url.openStream();
                imageCache.add(BitmapFactory.decodeStream(is));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s){
        viewPager.setAdapter(new explainViewPagerAdapter(context,imageCache));
        asyncDialog.dismiss();
    }
}
