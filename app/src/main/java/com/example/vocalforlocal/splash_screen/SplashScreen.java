package com.example.vocalforlocal.splash_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.vocalforlocal.R;
import com.example.vocalforlocal.walkthrough.WalkThrough;

public class SplashScreen extends AppCompatActivity {


    ImageView backgroung;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        getSupportActionBar().hide();

        backgroung = findViewById(R.id.image);
        lottieAnimationView = findViewById(R.id.animation);
        backgroung.animate().translationY(-1600).setDuration(1000).setStartDelay(3500);
        lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(3500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent =  new Intent(SplashScreen.this, WalkThrough.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        },3000);
    }
}