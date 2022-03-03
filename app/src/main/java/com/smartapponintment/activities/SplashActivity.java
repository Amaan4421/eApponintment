package com.smartapponintment.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.smartapponintment.R;
import com.smartapponintment.utils.GifImageView;

public class SplashActivity extends AppCompatActivity {

    int time = 2000;
    GifImageView imageView;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        img = findViewById(R.id.img);
        imageView = findViewById(R.id.civ_img);
        imageView.setGifImageResource(R.drawable.splash_gif);
        SharedPreferences sharedPreferences = getSharedPreferences("e_Appointment", MODE_PRIVATE);
        String strEmail = sharedPreferences.getString("KEY_PREF_EMAIL", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if (strEmail.equals("")){
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }else {
                    Intent i = new Intent(SplashActivity.this,BottomNavActivity.class);
                    startActivity(i);
                    finish();
                }
                           }
        },time);
    }
}