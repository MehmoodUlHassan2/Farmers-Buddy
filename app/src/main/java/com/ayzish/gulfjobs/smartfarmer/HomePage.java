package com.ayzish.gulfjobs.smartfarmer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayzish.gulfjobs.smartfarmer.activities.BlogsActivity;
import com.ayzish.gulfjobs.smartfarmer.activities.FertilizerCalculatorActivity;
import com.ayzish.gulfjobs.smartfarmer.activities.QueriesActivity;
import com.ayzish.gulfjobs.smartfarmer.activities.VideoTutorials;
import com.ayzish.gulfjobs.smartfarmer.activities.WeatherActivity;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Home Page");
        setContentView(R.layout.activity_home_page);

    }

    public void weatherUpdates(View view) {
        Intent intent = new Intent(HomePage.this, WeatherActivity.class);
        startActivity(intent);
    }

    public void videosTutorials(View view) {
        Intent intent = new Intent(HomePage.this, VideoTutorials.class);
        startActivity(intent);
    }

    public void blogTutorials(View view) {
        Intent intent = new Intent(HomePage.this, BlogsActivity.class);
        startActivity(intent);
    }

    public void fertilizerCalculator(View view) {
        Intent intent = new Intent(HomePage.this, FertilizerCalculatorActivity.class);
        startActivity(intent);
    }

    public void queries(View view) {
        Intent intent = new Intent(HomePage.this, QueriesActivity.class);
        startActivity(intent);
    }
}