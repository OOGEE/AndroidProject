package com.example.project;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.ui.main.SectionsPagerAdapter;

public class category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        String foodKind = getIntent().getStringExtra("foodName");
        switch(foodKind){
            case "koreanFood":
                viewPager.setCurrentItem(0);
                break;
            case "snackFood":
                viewPager.setCurrentItem(1);
                break;
            case "chineseFood":
                viewPager.setCurrentItem(2);
                break;
            case "chickenFood":
                viewPager.setCurrentItem(3);
                break;
            case "westernFood":
                viewPager.setCurrentItem(4);
                break;
            case "japaneseFood":
                viewPager.setCurrentItem(5);
                break;
        }
    }
}