package com.vingcoz.fantasia.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.pojo.SubCategories;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryRepository {

    public SubCategoryRepository() {
    }

    public LiveData<List<SubCategories>> getSubCategories(){
        MutableLiveData mutableLiveData=new MutableLiveData();

        List<SubCategories> subCategoriesList=new ArrayList<>();
        subCategoriesList.add(new SubCategories("Sarees", R.drawable.saree));
        subCategoriesList.add(new SubCategories("Churidhar",R.drawable.churidhar));
        subCategoriesList.add(new SubCategories("Kurthis",R.drawable.kurhis));
        subCategoriesList.add(new SubCategories("Chudi Bottom",R.drawable.chudi_bottoms));


        mutableLiveData.setValue(subCategoriesList);

        return mutableLiveData;
    }

//    public LiveData<List<Items>> getItems(){
//        MutableLiveData mutableLiveData=new MutableLiveData();
//        List<Items> itemsList=new ArrayList<>();
//        itemsList.add(new Items(100,"Iphone 11", R.drawable.iphone1,"15000.00"));
//        itemsList.add(new Items(101,"Note 10", R.drawable.note10,"25000.00"));
//        itemsList.add(new Items(102,"Note 10", R.drawable.note10,"50000.00"));
//        itemsList.add(new Items(101,"Galaxy f41", R.drawable.galaxyf41,"25000.00"));
//        itemsList.add(new Items(101,"Note 10", R.drawable.note10,"25000.00"));
//
//
//        mutableLiveData.setValue(itemsList);
//
//        return mutableLiveData;
//    }
}
