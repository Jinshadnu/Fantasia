package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.Wishlist;

import java.util.ArrayList;
import java.util.List;

public class WishlistRepository {

    public WishlistRepository() {
    }

    public LiveData<List<Wishlist>> getWishlist(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Wishlist> wishlists=new ArrayList<>();
        wishlists.add(new Wishlist(R.drawable.t_shirt,"Iphone11 Pro","Rs.1100000"));
        wishlists.add(new Wishlist(R.drawable.topwear,"Watch","Rs.900000"));
        wishlists.add(new Wishlist(R.drawable.bottomwear,"Iphone11 Pro","Rs.1100000"));

       mutableLiveData.setValue(wishlists);

       return mutableLiveData;


    }
}
