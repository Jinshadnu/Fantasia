package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.ActivityPriceDetailsBinding;
import com.vingcoz.fantasia.home.HomeActivity;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.OrderViewModel;

import java.util.Timer;
import java.util.TimerTask;

public class PriceDetailsActivity extends BaseActivity {
    public ActivityPriceDetailsBinding priceDetailsBinding;
    public String address_id, user_id, count, minimum, delivery_charge, price;
    public OrderViewModel orderViewModel;
    public Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        priceDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_price_details);

        priceDetailsBinding.layoutBase.textTitle.setText("Price Details");

        priceDetailsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        priceDetailsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        timer = new Timer();

        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);


        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MyPREFERENCES, MODE_PRIVATE);
        user_id = sharedPreferences.getString(Constants.USER_ID, null);
        address_id = getIntent().getStringExtra("address_id");
        count = getIntent().getStringExtra("count");
        minimum = getIntent().getStringExtra("minimum");
        price = getIntent().getStringExtra("price");
        delivery_charge = getIntent().getStringExtra("delivery_charge");

        priceDetailsBinding.minimum.setText(minimum);
        priceDetailsBinding.item.setText(count);
        priceDetailsBinding.shipping.setText(delivery_charge);
        priceDetailsBinding.total.setText(price);
        priceDetailsBinding.btnAddToCart.setText("Rs." + price);


        priceDetailsBinding.btnBuy.setOnClickListener(v -> {
            placeOrder();
        });


    }

    private void placeOrder() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            orderViewModel.placeOrder(user_id, address_id).observe(this, commonResponse -> {
                if (commonResponse != null && commonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    openSuccessDialog(commonResponse.getMessage());
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(PriceDetailsActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, 5000);
                }
            });
        }
    }

}