package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class explain extends AppCompatActivity {
    private String name;
    public String urlto;
    private ContentValues values;
    public String call, location, text;
    public String result;
    TextView StoreName, Explain, Phone;

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

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
