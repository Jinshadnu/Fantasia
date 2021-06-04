package com.vingcoz.fantasia.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.login.pojo.LoginResponse;
import com.vingcoz.fantasia.login.repository.LoginRepository;


public class LoginViewModel extends ViewModel {
    public LoginRepository loginRepository;

    public LoginViewModel() {
        this.loginRepository=new LoginRepository();
    }

    public LiveData<LoginResponse> login(String phone, String password){
        return loginRepository.login(phone, password);
    }

}
