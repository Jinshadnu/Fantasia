package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.repository.ProfileRepository;


public class ProfileViewModel extends ViewModel {
    public ProfileRepository profileRepository;

    public ProfileViewModel() {
        this.profileRepository=new ProfileRepository();
    }

    public LiveData<CommonResponse> editProfile(String userId, String phone, String email){
        return profileRepository.editProfile(userId, phone, email);
    }


}
