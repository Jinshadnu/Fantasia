package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.repository.ForgetPasswordRepository;


public class ForgotViewModel extends ViewModel {
    public ForgetPasswordRepository forgetPasswordRepository;

    public ForgotViewModel() {
        this.forgetPasswordRepository=new ForgetPasswordRepository();
    }

    public LiveData<CommonResponse> forgotPassword(String email, String userId){
        return forgetPasswordRepository.forgetPassword(email,userId);
    }
}
