package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Uri uri = Uri.parse("geo:0,0?q=35.868247,127.064557, 17z(경기장쉐~끼)");
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(it);
    }

    public void onClickKorenFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","koreanFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickSnackFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","snackFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickChineseFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","chineseFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickChickenFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","chickenFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickWesternFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","westernFood");
        startActivity(koreanFoodIntent);
    }

    public void onClickJapaneseFood(View v){
        Intent koreanFoodIntent = new Intent(getApplicationContext(), category.class);
        koreanFoodIntent.putExtra("foodName","japaneseFood");
        startActivity(koreanFoodIntent);
    }
}
