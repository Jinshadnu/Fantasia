package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordRepository {
    public NetworkInterface networkAPI;


    public ForgetPasswordRepository() {
    }

    public LiveData<CommonResponse> forgetPassword(String email, String user_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkAPI= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);

        Call<CommonResponse> responseCall=networkAPI.forgetPassword(email,user_id);

        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
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
