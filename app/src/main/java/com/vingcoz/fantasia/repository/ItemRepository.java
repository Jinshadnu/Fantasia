package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.Items;

import java.util.ArrayList;
import java.util.List;


public class ItemRepository {
    public LiveData<List<Items>> getItems(){
        MutableLiveData mutableLiveData=new MutableLiveData();
        List<Items> itemsList=new ArrayList<>();
        itemsList.add(new Items(100,"Saree", R.drawable.saree,"1500.00"));
        itemsList.add(new Items(101,"Note 10", R.drawable.churidhar,"25000.00"));
        itemsList.add(new Items(102,"Note 10", R.drawable.saree,"50000.00"));
        itemsList.add(new Items(101,"Galaxy f41", R.drawable.saree,"25000.00"));
        itemsList.add(new Items(101,"Note 10", R.drawable.saree,"25000.00"));


        mutableLiveData.setValue(itemsList);

        return mutableLiveData;
    }
}
