package com.vingcoz.fantasia.home.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.ItemsAdapter;
import com.vingcoz.fantasia.databinding.ActivityItemBinding;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.GridSpacingItemDecoration;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.ItemViewModel;
import com.vingcoz.fantasia.viewmodel.SubCategoryViewModel;

public class ItemActivity extends AppCompatActivity {
    public ItemViewModel itemViewModel;
    public ActivityItemBinding itemBinding;
    public ItemsAdapter itemsAdapter;
    public String subcategory_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemBinding= DataBindingUtil.setContentView(this,R.layout.activity_item);

        itemBinding.layoutBase.textTitle.setText("Select Product");

        itemBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        itemBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        subcategory_id=getIntent().getStringExtra("subcategory_id");

        itemViewModel= ViewModelProviders.of(this).get(ItemViewModel.class);



        itemBinding.recyclerProducts.setLayoutManager(new GridLayoutManager(this,2));
        itemBinding.recyclerProducts.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        itemBinding.recyclerProducts.addItemDecoration(dividerItemDecoration);
        itemBinding.recyclerProducts.setHasFixedSize(true);

        getItems();
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public void getItems(){
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()){
            itemViewModel.getItems(subcategory_id).observe(this,response -> {
                if(response != null && response.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    itemsAdapter=new ItemsAdapter(this,response.getItems());
                    itemBinding.recyclerProducts.setAdapter(itemsAdapter);

                }
            });
        }
    }
}