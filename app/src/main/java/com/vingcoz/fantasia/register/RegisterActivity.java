package com.vingcoz.fantasia.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityRegisterBinding;
import com.vingcoz.fantasia.home.HomeActivity;
import com.vingcoz.fantasia.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {
  public ActivityRegisterBinding registerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        registerBinding.buttonRegister.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
        });
        registerBinding.textViewLogin.setOnClickListener(v -> {
         startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}