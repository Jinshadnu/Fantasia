package com.vingcoz.fantasia.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.vingcoz.fantasia.pojo.AddressResponse;
import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.repository.AddressRepository;


public class AddressViewModel extends ViewModel {
    public AddressRepository addressRepository;

    public AddressViewModel() {
        this.addressRepository=new AddressRepository();
    }

    public LiveData<CommonResponse> addAddress(int address_status, String user_id, String address, String post, String pincode, String landmark){
        return addressRepository.addAddress(address_status, user_id, address, post, pincode, landmark);
    }

    public LiveData<AddressResponse> getAddress(String userId){
        return addressRepository.getAddress(userId);
    }


}
