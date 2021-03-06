package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.vingcoz.fantasia.R;


import java.util.List;

public class ProductImageAdapter extends PagerAdapter {
    private List<String> images;
    private LayoutInflater inflater;
    private Context context;
    public String pic1,pic2,pic3,pic4;
    public ProductImageAdapter(Context context, List<String> images, String pic1, String pic2, String pic3, String pic4) {
        this.context = context;
        this.images = images;
        this.pic1=pic1;
        this.pic2=pic2;
        this.pic3=pic3;
        this.pic4=pic4;
        inflater = LayoutInflater.from(this.context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.layout_product_slider, view, false);
        assert imageLayout != null;
        ImageView imageView = imageLayout.findViewById(R.id.iv_image);

        if(position == 1) {
            Glide.with(context)
                    .load(pic1)
                    .into(imageView);
        } else if (position == 2) {
            Glide.with(context)
                    .load(pic2)
                    .into(imageView);
        }
        else if (position == 3) {
            Glide.with(context)
                    .load(pic3)
                    .into(imageView);
        }
        else {
            Glide.with(context)
                    .load(pic4)
                    .into(imageView);
        }

        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}