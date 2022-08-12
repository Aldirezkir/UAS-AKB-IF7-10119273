package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class SlideActivity extends AppCompatActivity {
    ViewPager viewPager;
    SlideViewPageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        viewPager=findViewById(R.id.viewpager);
        adapter=new SlideViewPageAdapter(this);
        viewPager.setAdapter(adapter);
    }
}