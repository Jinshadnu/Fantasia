package com.vingcoz.fantasia.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivitySplashBinding;
import com.vingcoz.fantasia.welcome.WelcomeActivity;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;
    public ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        splashBinding= DataBindingUtil.setContentView(this,R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(i);

                finish();

            }
        },SPLASH_TIME_OUT);

    }
}