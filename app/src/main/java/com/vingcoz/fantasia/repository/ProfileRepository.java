package com.vingcoz.fantasia.repository;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.CommonResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    public NetworkInterface networkInterface;

    public LiveData<CommonResponse> editProfile(String userId, String phone, String email){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.editProfile(userId, phone, email);
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
