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
        wishlists.add(new Wishlist(R.drawable.churidhar,"Churidar","Rs.1100"));
        wishlists.add(new Wishlist(R.drawable.saree,"Saree","Rs.900"));
        wishlists.add(new Wishlist(R.drawable.chudi_bottoms,"Chudi","Rs.1100"));

       mutableLiveData.setValue(wishlists);

       return mutableLiveData;


    }
}
