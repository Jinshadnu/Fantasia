package com.vingcoz.fantasia.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityRegisterBinding;
import com.vingcoz.fantasia.home.HomeActivity;
import com.vingcoz.fantasia.login.LoginActivity;
import com.vingcoz.fantasia.register.viewmodel.RegisterViewModel;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class RegisterActivity extends BaseActivity {
    public ActivityRegisterBinding registerBinding;
    public RegisterViewModel registerViewModel;
    public ProgressDialog progressDialog;
    public String userName,password,phone,email,confirm_password;
    public int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        registerViewModel=new ViewModelProvider(this).get(RegisterViewModel.class);

        registerBinding.buttonRegister.setOnClickListener(v -> {
            if (validateFields()){
                register();
            }
        });
        registerBinding.textViewLogin.setOnClickListener(v -> {
         startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
    private boolean validateFields() {
        userName = requireNonNull(registerBinding.editTextTextPersonName.getText()).toString().trim();
        phone = requireNonNull(registerBinding.editTextTextPhone.getText()).toString().trim();
        email = requireNonNull(registerBinding.editTextTextPersonEmail.getText()).toString().trim();
        password = requireNonNull(registerBinding.editTextTextPassword.getText()).toString().trim();
        confirm_password=requireNonNull(registerBinding.editTextConfirmPassword.getText()).toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (isEmpty(userName)) {
            registerBinding.editTextTextPersonName.setError("Please enter your name");
            return false;
        }

        if (isEmpty(phone)) {
            registerBinding.editTextTextPhone.setError("Please enter phone number");
            return false;
        }

        if (isEmpty(email)) {
            registerBinding.editTextTextPersonEmail.setError("Please enter email");
            return false;
        }

        if (isEmpty(password)) {
            registerBinding.editTextTextPassword.setError("Please enter password");
            return false;
        }
        if (isEmpty(confirm_password)) {
            registerBinding.editTextConfirmPassword.setError("Please enter password");
            return false;
        }

        if (password.length() < 6){
            registerBinding.editTextTextPassword.setError("Password Must be atleast 6 characters");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() < 5){
            registerBinding.editTextTextPersonEmail.setError("Invalid email address");
            return false;
        }

        if(phone.length() < 8){
            registerBinding.editTextTextPhone.setError("Invalid phone number");
            return false;
        }

        return true;

    }
    public void  register(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            progressDialog =new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            registerViewModel.register(userName,phone,email,password,confirm_password).observe(this,registerResponse -> {
                progressDialog.dismiss();
                if (registerResponse !=null && registerResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    SharedPreferences sharedpreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedpreferences.edit();
                    editor.putBoolean(Constants.IsUserLogIn, true);
                    editor.putString(Constants.USER_ID,registerResponse.getUser().get(position).getUser_id());
                    editor.putString(Constants.USER_NAME,registerResponse.getUser().get(position).getUsername());
                    editor.putString(Constants.PHONE,registerResponse.getUser().get(position).getPhone());
                    editor.putString(Constants.EMAIL,registerResponse.getUser().get(position).getEmail());
                    editor.commit();
                    startActivity(new Intent(RegisterActivity.this,HomeActivity.class));
                    finish();
                }
                else {
                    showErrorSnackBar(this,"Registration Failed");
                }
                if (registerResponse != null && registerResponse.getStatus().equals(Constants.SERVER_RESPONSE_ERROR)){
                    showErrorSnackBar(this,"Email or phone already exsist");
                }



            });
        }
        else {
            showSnackBar(this,"Internet Connection not available");
        }
    }

}