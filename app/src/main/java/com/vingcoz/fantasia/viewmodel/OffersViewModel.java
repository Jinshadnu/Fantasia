package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.Offers;
import com.vingcoz.fantasia.repository.OffersRepository;

import java.util.List;

public class OffersViewModel extends ViewModel {
    public OffersRepository offersRepository;

    public OffersViewModel() {
        this.offersRepository=new OffersRepository();
    }


    public LiveData<List<Offers>> getOffers()
    {
        return offersRepository.getOffers();
    }
}
