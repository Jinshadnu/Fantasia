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
import com.vingcoz.fantasia.adapter.SubCategoryAdapter;
import com.vingcoz.fantasia.databinding.ActivitySubCategoryBinding;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.GridSpacingItemDecoration;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.SubCategoryViewModel;

public class SubCategoryActivity extends AppCompatActivity {
    public ActivitySubCategoryBinding subCatgBinding;
    public SubCategoryViewModel subCategoryViewModel;
    public SubCategoryAdapter subCategoryAdapter;
    public String category_id,subcategoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subCatgBinding= DataBindingUtil.setContentView(this,R.layout.activity_sub_category);

        subCatgBinding.layoutBase.textTitle.setText("Select SubCategory");

        subCatgBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        subCatgBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        category_id=getIntent().getStringExtra("category_id");

        subCategoryViewModel= ViewModelProviders.of(this).get(SubCategoryViewModel.class);

        subCatgBinding.recyclerSubcategory.setLayoutManager(new GridLayoutManager(this,2));
        subCatgBinding.recyclerSubcategory.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL);
        subCatgBinding.recyclerSubcategory.addItemDecoration(dividerItemDecoration);
        subCatgBinding.recyclerSubcategory.setHasFixedSize(true);

        getSubCategoris();

    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public void getSubCategoris() {
        if (NetworkUtilities.getNetworkInstance(this).isConnectedToInternet()) {
            subCategoryViewModel.getSubCategories(category_id).observe(this, subCategoryResponse -> {
                if (subCategoryResponse != null && subCategoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)) {
                    subCategoryAdapter = new SubCategoryAdapter(this, subCategoryResponse.getSubcategory());
                    subCatgBinding.recyclerSubcategory.setAdapter(subCategoryAdapter);
                }

            });
        }
    }
    }

