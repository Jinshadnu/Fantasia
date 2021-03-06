package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.LayoutSubcategoryBinding;
import com.vingcoz.fantasia.home.ui.activity.ItemActivity;
import com.vingcoz.fantasia.pojo.SubCategories;
import com.vingcoz.fantasia.pojo.SubCategoryResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.SubCategoryViewHolder> {
    public Context context;
    public List<SubCategoryResponse.Subcategory> subCategoriesList;

    public SubCategoryAdapter(Context context, List<SubCategoryResponse.Subcategory> subCategoriesList) {
        this.context = context;
        this.subCategoriesList = subCategoriesList;
    }

    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutSubcategoryBinding subcategoryBinding= DataBindingUtil.inflate(from(context), R.layout.layout_subcategory,parent,false);
        return new SubCategoryViewHolder(subcategoryBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
     SubCategoryResponse.Subcategory subCategories=subCategoriesList.get(position);
     holder.subcategoryBinding.setSubcategories(subCategories);
     holder.subcategoryBinding.cardSubcategory.setOnClickListener(v -> {
         Intent  intent=new Intent(context.getApplicationContext(),ItemActivity.class);
         String subcategory_id=subCategoriesList.get(position).getSubcategory_id();
         intent.putExtra("subcategory_id",subcategory_id);
         context.startActivity(intent);
     });
    }

    @Override
    public int getItemCount() {
        return subCategoriesList.size();
    }

    public class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        public LayoutSubcategoryBinding subcategoryBinding;
        public SubCategoryViewHolder(@NonNull LayoutSubcategoryBinding subcategoryBinding) {
            super(subcategoryBinding.getRoot());
            this.subcategoryBinding=subcategoryBinding;
        }
    }
}
