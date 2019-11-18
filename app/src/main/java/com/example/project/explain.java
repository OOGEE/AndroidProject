package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class explain extends AppCompatActivity {
    private String name;
    public String url;
    private ContentValues values;
    public String result;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);

        textView = findViewById(R.id.textView2);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        url = "http://toojs.asuscomm.com:8643/data/speciefStoreData/" + name;
        textView.setText(url);
    }

    public class NetworkTast1 extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result[] = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.
            return result[];
        }
    }
}
