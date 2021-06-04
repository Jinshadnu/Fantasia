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
        featuredProducts.add(new FeaturedProducts(R.drawable.saree,"Saree","2% off","Rs.1000/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.kurhis,"Kurthis","2% off","Rs.550/-"));
        featuredProducts.add(new FeaturedProducts(R.drawable.formal_shirt,"Shirt","2% off","Rs.1500/-"));

        mutableLiveData.setValue(featuredProducts);
        return  mutableLiveData;
    }
}
