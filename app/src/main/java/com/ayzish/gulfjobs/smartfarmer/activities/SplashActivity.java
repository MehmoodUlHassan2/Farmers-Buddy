package com.ayzish.gulfjobs.smartfarmer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayzish.gulfjobs.smartfarmer.HomePage;
import com.ayzish.gulfjobs.smartfarmer.R;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
    Animation topAnim, bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ImageView textView = findViewById(R.id.imageView);
        TextView textView1 = findViewById(R.id.textViewMain);

        textView.setAnimation(topAnim);
        textView1.setAnimation(bottomAnim);

            handler=new Handler();
            handler.postDelayed(() -> {
                Intent intent=new Intent(SplashActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            },3000);


    }
}