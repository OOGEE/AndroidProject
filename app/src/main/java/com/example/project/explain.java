package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ContentValues values;
    public String call, location, text;
    public String result;
    TextView StoreName, Explain, Phone;
    ImageView mainImage;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        urlto = "http://toojs.asuscomm.com:8643/data/speciefStoreData/" ;
        try {
            urlto += URLEncoder.encode(name.toString(),"utf-8");
            //Log.d("url parse",urlto);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StoreName = findViewById(R.id.StoreName);
        Explain = findViewById(R.id.Explain);
        Phone = findViewById(R.id.Phone);
        mainImage = findViewById(R.id.foodMain);

        StoreName.setText(name);

        NetworkTask1 networkTask = new NetworkTask1(urlto);
        networkTask.execute();
    }

    public void MapClick(View v){
        String MapData = "geo:0,0?q=" + location + ", 17z(" + name + ")";
        Uri uri = Uri.parse(MapData);
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    public class NetworkTask1 extends AsyncTask<Void, Void, String> {

        private String url;

        public NetworkTask1(String url) {

            this.url = url;
        }

        @Override
        protected String doInBackground(Void... params) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s){
            try {
                //JSONArray jsonArray = new JSONArray(result);
                //Log.d("JSON log",result);
                JSONObject jsonObjects = new JSONObject(result.substring(1,result.length()));
                call = jsonObjects.getString("call");
                location = jsonObjects.getString("location");
                text = jsonObjects.getString("text");

                Explain.setText(text);
                Phone.setText("전화 : " + call);
                getInternetImage(jsonObjects.getString("mainphotourl"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void getInternetImage(final String urlSource){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {    // 오래 거릴 작업을 구현한다
                // TODO Auto-generated method stub
                try{
                    // 걍 외우는게 좋다 -_-;
                    Log.d("image","image get succes : " + urlSource);
                    URL url = new URL(urlSource);

                    InputStream is = url.openStream();

                    final Bitmap bm = BitmapFactory.decodeStream(is);
                    handler.post(new Runnable() {

                        @Override
                        public void run() {  // 화면에 그려줄 작업
                            mainImage.setImageBitmap(bm);
                        }
                    });
                    mainImage.setImageBitmap(bm); //비트맵 객체로 보여주기
                } catch(Exception e){

                }

            }
        });

        t.start();

    }
}
