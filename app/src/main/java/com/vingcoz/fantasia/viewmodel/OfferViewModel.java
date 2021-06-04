package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.OfferResponse;
import com.vingcoz.fantasia.repository.OfferRepository;


public class OfferViewModel extends ViewModel {
    public OfferRepository offerRepository;

    public OfferViewModel() {
        this.offerRepository = new OfferRepository();
    }

    public LiveData<OfferResponse> getOffers(){
        return offerRepository.getOffers();
    }
}
