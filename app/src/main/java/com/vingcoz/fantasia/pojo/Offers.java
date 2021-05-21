package com.vingcoz.fantasia.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Offers {
    public int id;
    public String item_name;
    public int item_image;

    public Offers(String item_name, int item_image) {
        this.item_name = item_name;
        this.item_image = item_image;
    }

    @BindingAdapter("offers")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }




}
