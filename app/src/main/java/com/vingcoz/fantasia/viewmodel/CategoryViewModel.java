package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.Categories;
import com.vingcoz.fantasia.pojo.CategoryResponse;
import com.vingcoz.fantasia.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    public CategoryRepository categoryRepository;

    public CategoryViewModel() {
        this.categoryRepository=new CategoryRepository();
    }

    public LiveData<CategoryResponse> getCategories(){
        return categoryRepository.getCategories();
    }
}
