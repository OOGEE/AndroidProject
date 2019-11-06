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
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","koreanFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickSnackFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","snackFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickChineseFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","chineseFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickchickenFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","chickenFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickWesternFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","westernFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickJapaneseFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), menu.class);
        koreanFoodIntent.putExtra("foodName","japaneseFood");
        startActivity(koreanFoodIntent);
    }
}
