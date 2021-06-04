package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordRepository {
    public NetworkInterface networkAPI;

    public ChangePasswordRepository() {
    }

    public LiveData<CommonResponse> changePassword(String id, String oldPassword, String newPassword, String confirmpassword){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkAPI= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkAPI.changePassword(id,oldPassword,newPassword,confirmpassword);

        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();

                if (commonResponse != null){
                    mutableLiveData.postValue(commonResponse);
                }

            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
             mutableLiveData.postValue(null);
            }
        });
         return mutableLiveData;
    }
}
