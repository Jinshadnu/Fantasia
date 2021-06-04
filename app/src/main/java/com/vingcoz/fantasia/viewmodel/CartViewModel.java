package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.Cart;
import com.vingcoz.fantasia.pojo.CartResponse;
import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.repository.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    public CartRepository cartRepository;

    public CartViewModel() {
        this.cartRepository=new CartRepository();
    }

    public LiveData<CartResponse> getCart(String userId){
        return cartRepository.getCart(userId);
    }

    public LiveData<CommonResponse> addToCart(String size_name, String user_id, String quantity, String product_id){
        return cartRepository.addToCart(size_name,user_id,quantity,product_id);
    }
    public LiveData<CommonResponse> deletCart(String cart_id,String user_id){
        return cartRepository.deleteCart(cart_id,user_id);
    }
    public LiveData<CommonResponse> updateCart(String cart_id,String quantity){
        return cartRepository.updateCart(cart_id,quantity);
    }
}
