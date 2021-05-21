package com.vingcoz.fantasia.pojo;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SubCategories {
    public int sub_category_id;
    public String sub_category_name;

    public SubCategories(String sub_category_name, int sub_category_image) {
        this.sub_category_name = sub_category_name;
        this.sub_category_image = sub_category_image;
    }

    public int sub_category_image;


    public int getSub_category_image() {
        return sub_category_image;
    }


    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }
    @BindingAdapter("subcategory")
    public static void loadImage(ImageView imageView, int image){
        imageView.setImageResource(image);
    }







}
