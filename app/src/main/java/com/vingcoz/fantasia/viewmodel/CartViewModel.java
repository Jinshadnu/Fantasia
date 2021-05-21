package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.Cart;
import com.vingcoz.fantasia.repository.CartRepository;

import java.util.List;

public class CartViewModel extends ViewModel {
    public CartRepository cartRepository;

    public CartViewModel() {
        this.cartRepository=new CartRepository();
    }

    public LiveData<List<Cart>> getCart(){
        return cartRepository.getCart();
    }
}
