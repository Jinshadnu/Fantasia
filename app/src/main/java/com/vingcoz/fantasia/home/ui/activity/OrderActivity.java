package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.OrderAdapter;
import com.vingcoz.fantasia.databinding.ActivityOrderBinding;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.OrderViewModel;

public class OrderActivity extends BaseActivity implements OrderAdapter.cancelOrderListener {
    public ActivityOrderBinding orderBinding;
    public OrderViewModel orderViewModel;
    public OrderAdapter orderAdapter;
    public String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderBinding= DataBindingUtil.setContentView(this,R.layout.activity_order);

        orderBinding.layoutBase.textTitle.setText("My Orders");

        orderBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        orderBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        SharedPreferences sharedPreferences=getSharedPreferences(Constants.MyPREFERENCES,MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        orderBinding.recyclerMyorders.setLayoutManager(new LinearLayoutManager(this));

        orderBinding.recyclerMyorders.setHasFixedSize(true);
        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);

        getOrders();
    }

    private void getOrders() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            orderViewModel.getOrders(user_id).observe(this,orderResponse -> {
                if (orderResponse != null && orderResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    orderAdapter=new OrderAdapter(this,orderResponse.getOrders());
                    orderBinding.recyclerMyorders.setAdapter(orderAdapter);
                    orderAdapter.setCancelListener(this);

                }
                else{
                    orderBinding.textNodata.setVisibility(View.VISIBLE);
                    orderBinding.recyclerMyorders.setVisibility(View.GONE);
                    //orderBinding.searchview.setVisibility(View.GONE);
                }

            });
        }


    }

    @Override
    public void onOrderCancel(String orderId) {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            orderViewModel.cancelOrder(orderId).observe(this,comonResponse -> {
                if (comonResponse != null && comonResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    showSnackBar(this,comonResponse.getMessage());
                }
                else {
                    showErrorSnackBar(this,comonResponse.getMessage());
                }
            });
        }
        else {

        }
    }

}