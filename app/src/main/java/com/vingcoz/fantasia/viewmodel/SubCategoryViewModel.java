package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.SubCategories;
import com.vingcoz.fantasia.repository.SubCategoryRepository;

import java.util.List;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCategoryRepository;


    public SubCategoryViewModel() {
        subCategoryRepository=new SubCategoryRepository();
    }

    public LiveData<List<SubCategories>> getSubCategories(){
        return subCategoryRepository.getSubCategories();
    }

//    public LiveData<List<Items>> getItems(){
//        return subCategoryRepository.getItems();
//    }
}
