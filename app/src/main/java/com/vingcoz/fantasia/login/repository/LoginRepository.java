package com.vingcoz.fantasia.login.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.login.pojo.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    public NetworkInterface networkInterface;

    public LoginRepository() {

    }

    public LiveData<LoginResponse> login(String phone, String password){
        MutableLiveData mutableLiveData=new MutableLiveData();
        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<LoginResponse> responseCall=networkInterface.userLogin(phone, password);
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();
                if (loginResponse !=null){
                    mutableLiveData.postValue(loginResponse);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });
        return mutableLiveData;
    }
}
