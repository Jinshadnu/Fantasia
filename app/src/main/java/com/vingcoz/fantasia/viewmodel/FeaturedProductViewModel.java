package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.FeaturedProducts;
import com.vingcoz.fantasia.repository.FeaturedProductRepository;

import java.util.List;

public class FeaturedProductViewModel extends ViewModel {
    public FeaturedProductRepository featuredProductRepository;
    public FeaturedProductViewModel() {
        this.featuredProductRepository = new FeaturedProductRepository();

    }
    public LiveData<List<FeaturedProducts>> getFeaturedProducts(){
        return featuredProductRepository.getFearuredProducts();
    }
}
