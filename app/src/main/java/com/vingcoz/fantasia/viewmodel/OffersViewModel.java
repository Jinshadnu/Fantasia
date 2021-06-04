package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.vingcoz.fantasia.pojo.OfferResponse;
import com.vingcoz.fantasia.pojo.Offers;
import com.vingcoz.fantasia.repository.OfferRepository;
import com.vingcoz.fantasia.repository.OffersRepository;

import java.util.List;

public class OffersViewModel extends ViewModel {
    public OfferRepository offerRepository;

    public OffersViewModel() {
        this.offerRepository = new OfferRepository();
    }

    public LiveData<OfferResponse> getOffers(){
        return offerRepository.getOffers();
    }
}
