package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.core.NetworkInterface;
import com.vingcoz.fantasia.core.RetrofitClient;
import com.vingcoz.fantasia.pojo.Cart;
import com.vingcoz.fantasia.pojo.CartResponse;
import com.vingcoz.fantasia.pojo.CommonResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartRepository {
    public NetworkInterface networkInterface;

    public CartRepository() {
    }

    public LiveData<CartResponse> getCart(String userId){
        MutableLiveData mutableLiveData=new MutableLiveData();


        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CartResponse> responseCall=networkInterface.getCart(userId);
        responseCall.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                CartResponse cartResponse=response.body();
                if (cartResponse != null){
                    mutableLiveData.setValue(cartResponse);
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                mutableLiveData.setValue(null);
            }
        });


        return mutableLiveData;
    }

    public LiveData<CommonResponse> addToCart(String size_name, String user_id, String quantity, String product_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.addToCart(size_name,user_id,quantity,product_id);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse !=null){
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
    public LiveData<CommonResponse> deleteCart(String cart_id,String user_id){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.deleteCartItem(cart_id,user_id);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse !=null){
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
    public LiveData<CommonResponse> updateCart(String cart_id,String quantity){
        MutableLiveData mutableLiveData=new MutableLiveData();

        networkInterface= RetrofitClient.getRetrofitInstance().create(NetworkInterface.class);
        Call<CommonResponse> responseCall=networkInterface.updateCartItem(cart_id,quantity);
        responseCall.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                CommonResponse commonResponse=response.body();
                if (commonResponse !=null){
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
