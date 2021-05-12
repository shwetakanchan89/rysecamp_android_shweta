package com.rysecamp.controller.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.rysecamp.R;

public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_screen_first);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.splash_screen_first);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoLoginScreen();
            }
        }, 4000);

    }

    private void gotoHomescreen(){
        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
        finish();
    }

    private void gotoLoginScreen(){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        finish();
    }
}
