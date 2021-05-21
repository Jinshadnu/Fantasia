package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.LayoutWishlistBinding;
import com.vingcoz.fantasia.pojo.Wishlist;

import java.util.List;

import static android.view.LayoutInflater.from;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    public Context context;
    public List<Wishlist> wishlists;

    public WishlistAdapter(Context context, List<Wishlist> wishlists) {
        this.context = context;
        this.wishlists = wishlists;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutWishlistBinding wishlistBinding= DataBindingUtil.inflate(from(context), R.layout.layout_wishlist,parent,false);
        return new WishlistViewHolder(wishlistBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
    Wishlist wishlist=wishlists.get(position);
    holder.wishlistBinding.setWishlist(wishlist);
    }

    @Override
    public int getItemCount() {
        return wishlists.size();
    }

    public class WishlistViewHolder extends RecyclerView.ViewHolder {
        public LayoutWishlistBinding wishlistBinding;
        public WishlistViewHolder(@NonNull LayoutWishlistBinding wishlistBinding) {
            super(wishlistBinding.getRoot());
            this.wishlistBinding=wishlistBinding;
        }
    }
}
