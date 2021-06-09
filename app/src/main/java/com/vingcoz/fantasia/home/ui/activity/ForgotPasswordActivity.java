package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityForgotPasswordBinding;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.ForgotViewModel;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class ForgotPasswordActivity extends BaseActivity {
    public ActivityForgotPasswordBinding forgetPasswordBinding;
    public ForgotViewModel forgotViewModel;
    public ProgressDialog progressDialog;
    public String email,userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        forgetPasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);

        forgotViewModel= ViewModelProviders.of(this).get(ForgotViewModel.class);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        userId=sharedPreferences.getString(Constants.USER_ID,null);


        forgetPasswordBinding.buttonSubmit.setOnClickListener(v -> {
            if (validatefield()){
                forgetPassword();
            }
        });
    }

    public boolean validatefield(){
        email=requireNonNull(forgetPasswordBinding.edittextOldpassword.getText().toString().trim());

        if (isEmpty(email)){
            forgetPasswordBinding.edittextOldpassword.setError("please enter your email");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.length() < 5){
            forgetPasswordBinding.edittextOldpassword.setError("Invalid email address");
            return false;
        }
        return true;
    }

    public void forgetPassword(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            forgotViewModel.forgotPassword(email,userId).observe(this,comonResponse -> {
                progressDialog.dismiss();

                if(comonResponse != null && comonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,comonResponse.getMessage());
                    forgetPasswordBinding.edittextOldpassword.setText("");
                }
            });
        }
        else {
            showErrorSnackBar(this,"No Internet Connection");
        }
    }
}