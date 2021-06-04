package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.CartAdapter;
import com.vingcoz.fantasia.databinding.ActivityCartBinding;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.CartViewModel;

public class CartActivity extends AppCompatActivity implements CartAdapter.onDeleteListener,CartAdapter.setOnActionListener{
    public CartViewModel cartViewModel;
    public CartAdapter cartAdapter;
    public ActivityCartBinding cartBinding;
    public String user_id,total,cart_id,quantity;
    public String count;
    public String delivery_charge,minimum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cartBinding= DataBindingUtil.setContentView(this,R.layout.activity_cart);

        cartBinding.layoutBase.textTitle.setText("Cart");

        cartBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        cartBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });


        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);

        SharedPreferences sharedPreferences = getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);

        cartBinding.recyclerCartItems.setHasFixedSize(true);
        cartBinding.recyclerCartItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        getCartItems();
        cartBinding.btnBuy.setOnClickListener(view -> {
            Intent intent=new Intent(CartActivity.this,DefaultAddressActivity.class);
            count=String.valueOf(cartAdapter.cartList.size());
            String totalPrice=cartBinding.txtAmount.getText().toString();
            intent.putExtra("qauntity",count);
            intent.putExtra("price",totalPrice);
            intent.putExtra("minimum",minimum);
            intent.putExtra("delivery_charge",delivery_charge);
            startActivity(intent);
        });



    }

    public void getCartItems(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            cartViewModel.getCart(user_id).observe(this,cartResponse -> {
                if (cartResponse != null && cartResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    cartAdapter=new CartAdapter(this,cartResponse.getCart(),user_id);
                    delivery_charge=cartResponse.getDelivery_charge();
                    minimum=cartResponse.getMinimum_purchase_amount();
                    cartBinding.recyclerCartItems.setAdapter(cartAdapter);
                    total=cartResponse.getTotal_price();
                    cartBinding.txtAmount.setText(total);
                    cartAdapter.setDeleteListener(this);
                    cartAdapter.setActionListener(this);

                }
                else{
                    cartBinding.textNodata.setVisibility(View.VISIBLE);
                    cartBinding.recyclerCartItems.setVisibility(View.GONE);
                    cartBinding.linearCheckout.setVisibility(View.GONE);
                }
            });
        }

    }


    @Override
    public void onDelete(String userId, String cartId) {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            cartViewModel.deletCart(cartId,user_id).observe(this,commonResponse -> {
                Toast.makeText(CartActivity.this,commonResponse.getMessage(),Toast.LENGTH_LONG).show();
                getCartItems();
            });
        }
    }

    @Override
    public void onActionPerformed(String cart_id, String quantity) {
        this.cart_id=cart_id;
        this.quantity=quantity;
        updateCartItem();
    }

    public void updateCartItem(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            cartViewModel.updateCart(cart_id,quantity).observe(this,updateResponse -> {
                if (updateResponse != null && updateResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
//                    String cart_total=String.valueOf(updateResponse.ge);
//                    cartBinding.orederLayout.total.setText(cart_total+".00");
                    getCartItems();
                }
            });
        }
        else {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }

    }
}