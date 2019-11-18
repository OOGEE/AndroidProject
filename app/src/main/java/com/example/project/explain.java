package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class explain extends AppCompatActivity {
    private String name;
    public String urlto;
    private ContentValues values;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        urlto = "http://toojs.asuscomm.com:8643/data/speciefStoreData/" + name;

        NetworkTask1 networkTask = new NetworkTask1(urlto);
        networkTask.execute();

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
                JSONArray jsonArray = new JSONArray(result);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
