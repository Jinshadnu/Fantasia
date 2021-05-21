package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    public LiveData<List<Categories>> getCategories(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<Categories> categories=new ArrayList<>();
        categories.add(new Categories("Vismay", R.drawable.viismay));
        categories.add(new Categories("V-Star", R.drawable.vstar_logo));



        mutableLiveData.setValue(categories);
        return mutableLiveData;
    }
}
