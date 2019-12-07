package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
