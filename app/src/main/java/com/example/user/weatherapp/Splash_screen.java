package com.example.user.weatherapp;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Iterator;

import static android.view.View.*;

public class Splash_screen extends AppCompatActivity {
    int splash_timeout = 3000;
    TextView tv1, tv2, tv3, tv4;
    Path path;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout);
        tv1 = findViewById(R.id.text1);
        tv2 = findViewById(R.id.text2);
        tv3 = findViewById(R.id.text3);
        tv4 = findViewById(R.id.text4);
        final float startSize = 5; // Size in pixels
        final float endSize = 42;
        long animationDuration = 3000; // Animation duration in ms
        ValueAnimator animator = ValueAnimator.ofFloat(startSize, endSize);
        animator.setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                tv1.setTextSize(animatedValue);
                tv2.setTextSize(animatedValue);
                tv3.setTextSize(animatedValue);
            }
        });

        animator.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_screen.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, splash_timeout);
    }

}
