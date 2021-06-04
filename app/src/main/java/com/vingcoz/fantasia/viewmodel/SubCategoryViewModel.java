package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.SubCategories;
import com.vingcoz.fantasia.pojo.SubCategoryResponse;
import com.vingcoz.fantasia.repository.SubCategoryRepository;

import java.util.List;

public class SubCategoryViewModel extends ViewModel {
    public SubCategoryRepository subCategoryRepository;
    public SubCategoryViewModel() {
        this.subCategoryRepository=new SubCategoryRepository();
    }

    public LiveData<SubCategoryResponse> getSubCategories(String category_id){
        return subCategoryRepository.getSubcategory(category_id);
    }

}
