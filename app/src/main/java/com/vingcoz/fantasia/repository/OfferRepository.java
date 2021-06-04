package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.OfferResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferRepository {
    public NetworkInterface networkInterface;

    public OfferRepository() {
    }

    public LiveData<OfferResponse> getOffers(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<OfferResponse> responseCall=networkInterface.getSpecialOffers();
        responseCall.enqueue(new Callback<OfferResponse>() {
            @Override
            public void onResponse(Call<OfferResponse> call, Response<OfferResponse> response) {
             OfferResponse offerResponse=response.body();
             if (offerResponse != null){
                 mutableLiveData.setValue(offerResponse);
             }

            }

            @Override
            public void onFailure(Call<OfferResponse> call, Throwable t) {
             mutableLiveData.setValue(null);
            }
        });
     return mutableLiveData;
    }
}
