package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    public CartRepository() {
    }

    public LiveData<List<Cart>> getCart(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Cart> cartList=new ArrayList<>();
        cartList.add(new Cart("T-shirt","750", R.drawable.t_shirt));
        cartList.add(new Cart("T-shirt with Trouser","950", R.drawable.jeans_pant));
        cartList.add(new Cart("Track Pant","750", R.drawable.formal_shirt));



        mutableLiveData.setValue(cartList);

        return mutableLiveData;
    }
}
