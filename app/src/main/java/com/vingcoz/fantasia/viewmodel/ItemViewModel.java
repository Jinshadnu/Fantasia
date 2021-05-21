package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.Items;
import com.vingcoz.fantasia.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends ViewModel {
    public ItemRepository itemRepository;
    public ItemViewModel() {
        this.itemRepository=new ItemRepository();
    }
    public LiveData<List<Items>> getItems(){
        return itemRepository.getItems();
    }

}
