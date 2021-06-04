package com.vingcoz.fantasia.home.ui.cart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.CartAdapter;
import com.vingcoz.fantasia.databinding.FragmentCartBinding;
import com.vingcoz.fantasia.home.ui.activity.CartActivity;
import com.vingcoz.fantasia.home.ui.activity.DefaultAddressActivity;
import com.vingcoz.fantasia.home.ui.activity.ShippingAddressActivity;
import com.vingcoz.fantasia.pojo.Cart;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.CartViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartAdapter.onDeleteListener,CartAdapter.setOnActionListener {
    public CartViewModel cartViewModel;
    public CartAdapter cartAdapter;
    public FragmentCartBinding cartBinding;
    public String user_id,total,cart_id,quantity;
    public String count;
    public String delivery_charge,minimum;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        cartViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(CartViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        cartBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_cart,container,false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,null);



        cartBinding.recyclerCartItems.setHasFixedSize(true);
        cartBinding.recyclerCartItems.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));


        getCartItems();

        cartBinding.btnBuy.setOnClickListener(view -> {
            Intent intent=new Intent(getActivity(),DefaultAddressActivity.class);
            count=String.valueOf(cartAdapter.cartList.size());
            String totalPrice=cartBinding.txtAmount.getText().toString();
            intent.putExtra("qauntity",count);
            intent.putExtra("price",totalPrice);
            intent.putExtra("minimum",minimum);
            intent.putExtra("delivery_charge",delivery_charge);
            startActivity(intent);
        });
        return cartBinding.getRoot();
    }

    public void getCartItems(){
       if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            cartViewModel.getCart(user_id).observe(getActivity(),cartResponse -> {
                if (cartResponse != null && cartResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    cartAdapter=new CartAdapter(getActivity(),cartResponse.getCart(),user_id);
                    delivery_charge=cartResponse.getDelivery_charge();
                    minimum=cartResponse.getMinimum_purchase_amount();


                    cartBinding.recyclerCartItems.setAdapter(cartAdapter);
                    total=cartResponse.getTotal_price();
                    cartBinding.txtAmount.setText(total);
                    cartAdapter.setDeleteListener(this);
                    cartAdapter.setActionListener(this);

                }
                else {
                    cartBinding.textNodata.setVisibility(View.VISIBLE);
                    cartBinding.recyclerCartItems.setVisibility(View.GONE);
                    cartBinding.linearCheckout.setVisibility(View.GONE);
                }

            });
        }
    }

    @Override
    public void onDelete(String userId, String cartId) {
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            cartViewModel.deletCart(cartId,user_id).observe(this.getActivity(),commonResponse -> {
                Toast.makeText(getActivity(),commonResponse.getMessage(),Toast.LENGTH_LONG).show();
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
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            cartViewModel.updateCart(cart_id,quantity).observe(getActivity(),updateResponse -> {
                if (updateResponse != null && updateResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
//                    String cart_total=String.valueOf(updateResponse.ge);
//                    cartBinding.orederLayout.total.setText(cart_total+".00");
                    getCartItems();
                }
            });
        }
        else {
            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }

    }

}