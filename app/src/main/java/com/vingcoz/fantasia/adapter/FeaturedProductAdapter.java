package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.LayoutFeaturedBinding;
import com.vingcoz.fantasia.home.ui.activity.ProductDetailsActivity;
import com.vingcoz.fantasia.pojo.FeaturedProducts;
import com.vingcoz.fantasia.pojo.OfferResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class FeaturedProductAdapter extends RecyclerView.Adapter<FeaturedProductAdapter.FeaturedViewHolder> {
    public Context context;
    public List<OfferResponse.Special_offer_items> featuredProductsList;


    public FeaturedProductAdapter(Context context, List<OfferResponse.Special_offer_items> featuredProductsList) {
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
        OfferResponse.Special_offer_items featuredProducts=featuredProductsList.get(position);
        holder.featuredBinding.setFeaturedproducts(featuredProducts);
        holder.featuredBinding.cardSpecialoffer.setOnClickListener(v -> {
            Intent intent=new Intent(context.getApplicationContext(), ProductDetailsActivity.class);
            intent.putExtra("item_id",featuredProductsList.get(position).getItem_id());
            intent.putExtra("item_name",featuredProductsList.get(position).getItem_name());
            intent.putExtra("item_price",featuredProductsList.get(position).getItem_price());
            intent.putExtra("item_decription",featuredProductsList.get(position).getItem_description());
            intent.putExtra("pic1",featuredProductsList.get(position).getPic1());
            intent.putExtra("pic2",featuredProductsList.get(position).getPic2());
            intent.putExtra("pic3",featuredProductsList.get(position).getPic3());
            intent.putExtra("pic4",featuredProductsList.get(position).getPic4());
            context.startActivity(intent);

        });
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
