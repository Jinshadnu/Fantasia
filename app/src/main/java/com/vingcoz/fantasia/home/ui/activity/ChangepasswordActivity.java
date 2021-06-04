package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityChangepasswordBinding;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.ChangePasswordViewModel;

import static android.text.TextUtils.isEmpty;
import static java.util.Objects.requireNonNull;

public class ChangepasswordActivity extends BaseActivity {
    public ChangePasswordViewModel passwordViewModel;
    public ProgressDialog progressDialog;
    public ActivityChangepasswordBinding changePasswordBinding;
    public String oldPassword,newPassword,confirmPassword;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changePasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_changepassword);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);

        id=sharedPreferences.getString(Constants.USER_ID,null);



        passwordViewModel= ViewModelProviders.of(this).get(ChangePasswordViewModel.class);

        changePasswordBinding.buttonSubmit.setOnClickListener(v -> {
            if (validatefield()){
                changePassword();
            }
        });




    }

    public boolean validatefield(){
        oldPassword= requireNonNull(changePasswordBinding.edittextOldpassword.getText().toString().trim());
        newPassword= requireNonNull(changePasswordBinding.edittextNewpassword.getText().toString().trim());
        confirmPassword=requireNonNull(changePasswordBinding.edittextConfirmpassword.getText().toString().trim());

        if (isEmpty(oldPassword)){
            changePasswordBinding.edittextOldpassword.setError("please enter your password");
            return false;
        }

        if (isEmpty(newPassword)){
            changePasswordBinding.edittextNewpassword.setError("please enter your new password");
            return false;
        }

        if (isEmpty(confirmPassword)){
            changePasswordBinding.edittextConfirmpassword.setError("please confirm your password");
            return false;
        }


        if (newPassword.length() < 6){
            changePasswordBinding.edittextNewpassword.setError("Password must be atleast 6 characters");
            return false;
        }

        if(!newPassword.equals(confirmPassword)){
            changePasswordBinding.edittextConfirmpassword.setError("Password not matching");
            return false;
        }

        return true;
    }
    public void changePassword(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("Loading....");
            progressDialog.show();

            passwordViewModel.changePassword(id,oldPassword,newPassword,confirmPassword).observe(this,commonResponse -> {
                progressDialog.dismiss();

                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,commonResponse.getMessage());
                    changePasswordBinding.edittextOldpassword.setText(" ");
                    changePasswordBinding.edittextNewpassword.setText(" ");
                    changePasswordBinding.edittextConfirmpassword.setText(" ");
                    finish();
                }
                else {
                    showSnackBar(this,commonResponse.getMessage());
                }
            });
        }
        else {
            showErrorSnackBar(this,"No Internet Connection");
        }
    }
}