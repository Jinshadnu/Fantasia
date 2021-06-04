package com.vingcoz.fantasia.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityLoginBinding;
import com.vingcoz.fantasia.home.HomeActivity;
import com.vingcoz.fantasia.home.ui.activity.ForgotPasswordActivity;
import com.vingcoz.fantasia.login.viewmodel.LoginViewModel;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class LoginActivity extends BaseActivity {
    public ActivityLoginBinding loginBinding;
    public LoginViewModel loginViewModel;
    public ProgressDialog progressDialog;
    public String username, password;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBinding.textForgetpassword.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        });

        loginBinding.buttonLogin.setOnClickListener(v -> {
            if (validatefields()) {
                login();
            }
        });

    }

    public boolean validatefields() {
        username = requireNonNull(loginBinding.editTextUsername.getText().toString().trim());
        password = requireNonNull(loginBinding.editTextPassword.getText().toString().trim());

        if (isEmpty(username)) {
            loginBinding.editTextUsername.setError("please enter your phone number");
            return false;
        }

        if (isEmpty(password)) {
            loginBinding.editTextPassword.setError("please enter password");
            return false;
        }

        if(username.length() < 10){
            loginBinding.editTextUsername.setError("Invalid phone number");
            return false;
        }

        return true;
    }

    public void login() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setMessage("Please wait");
            progressDialog.show();

            loginViewModel.login(username, password).observe(this, loginResponse -> {
                progressDialog.dismiss();
                if (loginResponse != null && loginResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(Constants.IsUserLogIn, true);
                    editor.putString(Constants.USER_ID, loginResponse.getUser().get(position).getUser_id());
                    editor.putString(Constants.USER_NAME, loginResponse.getUser().get(position).getUsername());
                    editor.putString(Constants.PHONE, loginResponse.getUser().get(position).getPhone());
                    editor.putString(Constants.EMAIL, loginResponse.getUser().get(position).getEmail());
                    editor.commit();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    showSnackBar(LoginActivity.this, loginResponse.getMessage());
                }
            });
        } else {
            showSnackBar(this, "Internet Connection not available");
        }
    }
}