package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.OrderAdapter;
import com.vingcoz.fantasia.databinding.ActivityMyOrderBinding;
import com.vingcoz.fantasia.util.BaseActivity;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.OrderViewModel;

public class MyOrderActivity extends BaseActivity {
    public ActivityMyOrderBinding myOrderBinding;
    public OrderViewModel orderViewModel;
    public OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        myOrderBinding= DataBindingUtil.setContentView(this,R.layout.activity_my_order);

        myOrderBinding.recyclerMyorders.setLayoutManager(new LinearLayoutManager(this));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL);
//        myOrderBinding.recyclerMyorders.addItemDecoration(dividerItemDecoration);
        myOrderBinding.recyclerMyorders.setHasFixedSize(true);
        orderViewModel= ViewModelProviders.of(this).get(OrderViewModel.class);

       // getOrders();
    }


}