package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.ShipAddressAdapter;
import com.vingcoz.fantasia.databinding.ActivityDefaultAddressBinding;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.AddressViewModel;

public class DefaultAddressActivity extends AppCompatActivity {
    public ActivityDefaultAddressBinding defaultAddressBinding;
    public String user_id,name,address_id;
    public AddressViewModel addressViewModel;
    public ShipAddressAdapter shipAddressAdapter;
    public String count,price,delivery_charge,minimum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defaultAddressBinding= DataBindingUtil.setContentView(this,R.layout.activity_default_address);

        defaultAddressBinding.layoutBase.textTitle.setText("Shipping Address");

        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);
        name=sharedPreferences.getString(Constants.USER_NAME,null);

        count=getIntent().getStringExtra("qauntity");
        price=getIntent().getStringExtra("price");
        minimum=getIntent().getStringExtra("minimum");
        delivery_charge=getIntent().getStringExtra("delivery_charge");




        defaultAddressBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        defaultAddressBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        addressViewModel=new ViewModelProvider(this).get(AddressViewModel.class);

        defaultAddressBinding.recyclerAddress.setLayoutManager(new LinearLayoutManager(this));
        defaultAddressBinding.recyclerAddress.setHasFixedSize(true);

        defaultAddressBinding.buttonAdd.setOnClickListener(v -> {
            startActivity(new Intent(DefaultAddressActivity.this,AddressActivity.class));
        });

        getAddress();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddress();
    }

    //    public void itemClicked(View v) {
//        //code to check if this checkbox is checked!
//
//        if(defaultAddressBinding.checkBox.isChecked()){
//            getAddress();
//            defaultAddressBinding.cardAddress.setVisibility(View.VISIBLE);
//        }
//    }


    public void getAddress(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            addressViewModel.getAddress(user_id).observe(this,addressResponse -> {
                if (addressResponse != null && addressResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    shipAddressAdapter=new ShipAddressAdapter(this,addressResponse.getAddress(),count,price,minimum,delivery_charge);
                    defaultAddressBinding.recyclerAddress.setAdapter(shipAddressAdapter);
                }
            });
        }
    }
}