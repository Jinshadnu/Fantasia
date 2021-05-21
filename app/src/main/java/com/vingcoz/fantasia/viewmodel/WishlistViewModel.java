package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.Wishlist;
import com.vingcoz.fantasia.repository.WishlistRepository;

import java.util.List;

public class WishlistViewModel extends ViewModel {
    public WishlistRepository wishlistRepository;

    public WishlistViewModel() {
        this.wishlistRepository=new WishlistRepository();
    }

    public LiveData<List<Wishlist>> getWishlist(){
        return wishlistRepository.getWishlist();
    }
}
