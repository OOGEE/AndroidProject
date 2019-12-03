package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
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
        Intent KoreanFoodIntent = new Intent(getApplicationContext(), category.class);
        KoreanFoodIntent.putExtra("foodName","koreanFood");
        startActivity(KoreanFoodIntent);
    }

    public void onClickSnackFood(View v){
        Intent SnackFoodIntent = new Intent(getApplicationContext(), category.class);
        SnackFoodIntent.putExtra("foodName","snackFood");
        startActivity(SnackFoodIntent);
    }

    public void onClickChineseFood(View v){
        Intent ChineseFoodIntent = new Intent(getApplicationContext(), category.class);
        ChineseFoodIntent.putExtra("foodName","chineseFood");
        startActivity(ChineseFoodIntent);
    }

    public void onClickChickenFood(View v){
        Intent ChickenIntent = new Intent(getApplicationContext(), category.class);
        ChickenIntent.putExtra("foodName","chickenFood");
        startActivity(ChickenIntent);
    }

    public void onClickWesternFood(View v){
        Intent WesternFoodIntent = new Intent(getApplicationContext(), category.class);
        WesternFoodIntent.putExtra("foodName","westernFood");
        startActivity(WesternFoodIntent);
    }

    public void onClickJapaneseFood(View v){
        Intent JapaneseFoodIntent = new Intent(getApplicationContext(), category.class);
        JapaneseFoodIntent.putExtra("foodName","japaneseFood");
        startActivity(JapaneseFoodIntent);
    }

}
