package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.Offers;

import java.util.ArrayList;
import java.util.List;

public class OffersRepository {
    public LiveData<List<Offers>> getOffers(){
        MutableLiveData mutableLiveData=new MutableLiveData();
        List<Offers> offers=new ArrayList<>();
        offers.add(new Offers("T-shirt", R.drawable.t_shirt));
        offers.add(new Offers("Pant", R.drawable.jeans_pant));
        offers.add(new Offers("Shirt", R.drawable.casual_shirt));

        mutableLiveData.setValue(offers);

        return mutableLiveData;
    }
}
