package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.FeaturedProducts;

import java.util.ArrayList;
import java.util.List;

public class FeaturedProductRepository {

    public FeaturedProductRepository() {
    }

    public LiveData<List<FeaturedProducts>> getFearuredProducts(){
        MutableLiveData mutableLiveData=new
                MutableLiveData();
        List<FeaturedProducts> featuredProducts=new ArrayList<>();
        featuredProducts.add(new FeaturedProducts(R.drawable.topwear,"Watch","2% off","Rs.550000/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.t_shirt,"Iphone11pro","2% off","Rs.550000/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.formal_shirt,"Shoes","2% off","Rs.550000/-"));

        mutableLiveData.setValue(featuredProducts);
        return  mutableLiveData;
    }
}
