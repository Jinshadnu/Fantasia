package com.vingcoz.fantasia.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerResponse {
    @SerializedName("banners")
    public ArrayList<Banners> banners;

    @SerializedName("status")
    public String status;

    public ArrayList<Banners> getBanners() {
        return banners;
    }

    public String getStatus() {
        return status;
    }

    public class Banners {
        @SerializedName("images")
        public String banner_images;

        public String getBanner_images() {
            return banner_images;
        }
    }
}
