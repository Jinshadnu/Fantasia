package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.pojo.Order;
import com.vingcoz.fantasia.pojo.OrderResponse;
import com.vingcoz.fantasia.pojo.OrderedItemResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {
    public NetworkInterface networkInterface;


    public OrderRepository() {
    }

    public LiveData<OrderResponse> getOrders(String userId){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<OrderResponse> responseCall=networkInterface.getOrders(userId);
        responseCall.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse=response.body();
                if (orderResponse !=null){
                    mutableLiveData.setValue(orderResponse);
                }
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;

    }

    public LiveData<CommonResponse> placeOrder(String userId, String order_address){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.placeOrder(userId, order_address);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse !=null){
                    mutableLiveData.setValue(commonResponse);
                }
            }

            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;

    }

    public LiveData<OrderedItemResponse> getOrderedItems(String orderId){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<OrderedItemResponse> responseCall=networkInterface.getOrderedItems(orderId);
        responseCall.enqueue(new Callback<OrderedItemResponse>() {
            @Override
            public void onResponse(Call<OrderedItemResponse> call, Response<OrderedItemResponse> response) {
                OrderedItemResponse orderedItemResponse=response.body();
                if (orderedItemResponse !=null){
                    mutableLiveData.setValue(orderedItemResponse);
                }
            }

            @Override
            public void onFailure(Call<OrderedItemResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;

    }
    public LiveData<CommonResponse> cancelOrder(String orderId){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface=RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.orderCancel(orderId);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse comonResponse=response.body();
                if (comonResponse != null){
                    mutableLiveData.postValue(comonResponse);
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
