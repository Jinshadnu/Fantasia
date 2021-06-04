package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.ItemResponse;
import com.vingcoz.fantasia.pojo.Items;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemRepository {
    public NetworkInterface networkInterface;

    public ItemRepository() {
    }

    public LiveData<ItemResponse> getItems(String subcategory_id){
        MutableLiveData mutableLiveData=new MutableLiveData();


        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<ItemResponse> responseCall=networkInterface.getItems(subcategory_id);
        responseCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                ItemResponse itemResponse=response.body();
                if (itemResponse != null){
                    mutableLiveData.setValue(itemResponse);
                }
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
//

        return mutableLiveData;
    }
}
