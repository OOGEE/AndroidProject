package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 /*       Uri uri = Uri.parse("geo:0,0?q=35.868247,127.064557, 17z(경기장쉐~끼)");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
*/
    }

    public void onClickKorenFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/0";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent KoreanFoodIntent = new Intent(getApplicationContext(), category.class);
        KoreanFoodIntent.putExtra("foodName","koreanFood");
        KoreanFoodIntent.putExtra("json", result);
        startActivity(KoreanFoodIntent);
    }

    public void onClickSnackFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/1";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent SnackFoodIntent = new Intent(getApplicationContext(), category.class);
        SnackFoodIntent.putExtra("foodName","snackFood");
        SnackFoodIntent.putExtra("json", result);
        startActivity(SnackFoodIntent);
    }

    public void onClickChineseFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/2";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent ChineseFoodIntent = new Intent(getApplicationContext(), category.class);
        ChineseFoodIntent.putExtra("foodName","chineseFood");
        ChineseFoodIntent.putExtra("json", result);
        startActivity(ChineseFoodIntent);
    }

    public void onClickChickenFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/3";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent ChickenIntent = new Intent(getApplicationContext(), category.class);
        ChickenIntent.putExtra("foodName","chickenFood");
        ChickenIntent.putExtra("json", result);
        startActivity(ChickenIntent);
    }

    public void onClickWesternFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/4";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent WesternFoodIntent = new Intent(getApplicationContext(), category.class);
        WesternFoodIntent.putExtra("foodName","westernFood");
        WesternFoodIntent.putExtra("json", result);
        startActivity(WesternFoodIntent);
    }

    public void onClickJapaneseFood(View v){
        String url = "http://toojs.asuscomm.com:8643/data/storeKindData/5";
        NetworkTask networkTask = new NetworkTask(url, null);
        networkTask.execute();
        Intent JapaneseFoodIntent = new Intent(getApplicationContext(), category.class);
        JapaneseFoodIntent.putExtra("foodName","japaneseFood");
        JapaneseFoodIntent.putExtra("json", result);
        startActivity(JapaneseFoodIntent);
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }
    }
}
