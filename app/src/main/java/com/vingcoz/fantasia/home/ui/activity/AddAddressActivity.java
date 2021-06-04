package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.AddressAdapter;
import com.vingcoz.fantasia.databinding.ActivityAddAddressBinding;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.AddressViewModel;

public class AddAddressActivity extends AppCompatActivity {
    public ActivityAddAddressBinding addAddressBinding;
    public AddressViewModel addressViewModel;
    public String user_id;
    public AddressAdapter addressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_add_address);

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        addAddressBinding.layoutBase.textTitle.setText("Address");

        addAddressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        addAddressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });



        addAddressBinding.buttonAdd.setOnClickListener(v -> {
            startActivity(new Intent(AddAddressActivity.this,AddressActivity.class));
        });

        addAddressBinding.recyclerAddress.setLayoutManager(new LinearLayoutManager(this));
        addAddressBinding.recyclerAddress.setHasFixedSize(true);

        getAddress();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddress();
    }

    public void getAddress(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            addressViewModel.getAddress(user_id).observe(this,addressResponse -> {
                if (addressResponse != null && addressResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    addressAdapter=new AddressAdapter(this,addressResponse.getAddress());
                    addAddressBinding.recyclerAddress.setAdapter(addressAdapter);
                }
            });
        }
    }
}