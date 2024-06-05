package com.stylish.fancy.text.generator.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.stylish.fancy.text.generator.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigateToHomeActivity();
            }
        }, 800);
    }

    private void navigateToHomeActivity() {
        if (!isFinishing()) {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}