package com.example.project;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class NetworkTask extends AsyncTask<Void, Void, String> {

    public String url;
    public ContentValues values;
    public Integer foodKind;
    public View root;
    public Context ctx;
    public ListView mList;
    public foodStroeLIstAdapter mAdapter;
    public ArrayList<foodStoreListData> mData = new ArrayList<foodStoreListData>();

    ProgressDialog asyncDialog;

    public NetworkTask(String url, ContentValues values,Integer foodKind,View root,Context ctx) {
        this.url = url;
        this.values = values;
        this.foodKind = foodKind;
        this.root = root;
        this.ctx = ctx;
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
        String result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
        try {
            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++){
                final JSONObject jsonObjects = jsonArray.getJSONObject(i);
                try {
                    URL url = new URL(jsonObjects.getString("mainphotourl"));
                    InputStream is = url.openStream();
                    mData.add(new foodStoreListData(BitmapFactory.decodeStream(is),jsonObjects.getString("name")));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //@Override
    protected void onPostExecute(String s) {
        asyncDialog.dismiss();

        mList = (ListView) root.findViewById(R.id.foodStoreList);
        mAdapter = new foodStroeLIstAdapter(ctx,mData);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ctx,explain.class);
                intent.putExtra("name",mData.get(position).name);
                ctx.startActivity(intent);
            }
        });

        super.onPostExecute(s);
    }

    public void getInternetImage(final String urlSource,final String name){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(urlSource);
                    InputStream is = url.openStream();
                    mData.add(new foodStoreListData(BitmapFactory.decodeStream(is),name));
                    Log.d("thread","get photo");
                    //mAdapter.notifyDataSetChanged();
                }
                catch(Exception e){
                    Log.d("thread",e.toString());
                }
            }
        });
        t.start();
    }

}
