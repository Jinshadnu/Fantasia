package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.BannerResponse;
import com.vingcoz.fantasia.repository.BannerRepository;


public class BannerViewModel extends ViewModel {
    public BannerRepository bannerRepository;

    public BannerViewModel() {
        this.bannerRepository=new BannerRepository();
    }

    public LiveData<BannerResponse> getBanners(){
        return bannerRepository.getBanners();
    }
}
