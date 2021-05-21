package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.LayoutFeaturedBinding;
import com.vingcoz.fantasia.pojo.FeaturedProducts;

import java.util.List;

import static android.view.LayoutInflater.from;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.FeaturedViewHolder> {
    public Context context;
    public List<FeaturedProducts> featuredProductsList;

    public FeaturedProductAdapter(Context context, List<FeaturedProducts> featuredProductsList) {
        this.context = context;
        this.featuredProductsList = featuredProductsList;
    }


    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutFeaturedBinding subitemsBinding= DataBindingUtil.inflate(from(context), R.layout.layout_featured,parent,false);
        return new FeaturedProductAdapter.FeaturedViewHolder(subitemsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
     FeaturedProducts featuredProducts=featuredProductsList.get(position);
     holder.featuredBinding.setFeaturedproducts(featuredProducts);
    }

    @Override
    public int getItemCount() {
        return featuredProductsList.size();
    }

    public class FeaturedViewHolder extends RecyclerView.ViewHolder {
        public LayoutFeaturedBinding featuredBinding;
        public FeaturedViewHolder(@NonNull LayoutFeaturedBinding featuredBinding) {
            super(featuredBinding.getRoot());
            this.featuredBinding=featuredBinding;

        }
    }
}
