package com.vingcoz.fantasia.core;


import com.vingcoz.fantasia.login.pojo.LoginResponse;
import com.vingcoz.fantasia.pojo.AddressResponse;
import com.vingcoz.fantasia.pojo.BannerResponse;
import com.vingcoz.fantasia.pojo.CartResponse;
import com.vingcoz.fantasia.pojo.CategoryResponse;
import com.vingcoz.fantasia.pojo.CommonResponse;
import com.vingcoz.fantasia.pojo.ItemResponse;
import com.vingcoz.fantasia.pojo.OfferResponse;
import com.vingcoz.fantasia.pojo.OrderResponse;
import com.vingcoz.fantasia.pojo.OrderedItemResponse;
import com.vingcoz.fantasia.pojo.SubCategoryResponse;
import com.vingcoz.fantasia.register.pojo.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface NetworkInterface {
    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> userRegistration(@Field("name") String name, @Field("mob")String phone, @Field("email")String email, @Field("password")String password, @Field("confirm_password")String confirm_password);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> userLogin(@Field("phone")String phone, @Field("password")String password);

    @GET("getCategories")
    Call<CategoryResponse> getCategories();

    @GET("getSubcategories")
    Call<SubCategoryResponse> getSubCategories(@Query("category_id")String category_id);

    @GET("getItems")
    Call<ItemResponse> getItems(@Query("subcategory_id")String subcategory_id);

    @FormUrlEncoded
    @POST("addToCart")
    Call<CommonResponse> addToCart(@Field("size_name")String sizename, @Field("user_id")String user_id, @Field("qty")String quantity, @Field("product_id")String product_id);

    @GET("get_cart")
    Call<CartResponse> getCart(@Query("user_id")String user_id);

     @GET("get_slider_images")
     Call<BannerResponse> getSliderImages();

//    @FormUrlEncoded
//    @POST("add_to_favourites")
//    Call<CommonResponse> addToFavourites(@Field("user_id")String user_id,@Field("item_id")String product_id,@Field("fav_status")int status);
//
//    @GET("get_favourites")
//    Call<FavouritesResponse> getFavourites(@Query("user_id")String user_id);
//
//    @FormUrlEncoded
//    @POST("edit_profile")
//    Call<CommonResponse> editProfile(@Field("user_id")String userId,@Field("phone") String phone,@Field("email") String email);
//
    @FormUrlEncoded
    @POST("change_password")
    Call<CommonResponse> changePassword(@Field("user_id") String id, @Field("old_password") String oldPassword, @Field("new_password")String newPassword,@Field("confirm_password")String confirPassword);

//    @FormUrlEncoded
//    @POST("forgot_password")
//    Call<CommonResponse> forgetPassword(@Field("email_id") String email,@Field("user_id")String user_id);
//
    @GET("get_special_offers")
    Call<OfferResponse> getSpecialOffers();
//
   @FormUrlEncoded
   @POST("add_address")
   Call<CommonResponse> addAddress(@Field("address_status")int address_status,@Field("user_id")String user_id,@Field("address")String address,@Field("post")String post,@Field("pincode")String pincode,@Field("landmark")String landmark);

   @GET("get_address")
    Call<AddressResponse> getAddress(@Query("user_id")String user_id);
//
    @FormUrlEncoded
    @POST("delete_cart_item")
    Call<CommonResponse> deleteCartItem(@Field("cart_id")String cart_id,@Field("user_id")String user_id);
//
    @GET("getorders")
    Call<OrderResponse> getOrders(@Query("user_id")String user_id);
//
    @FormUrlEncoded
    @POST("place_order")
    Call<CommonResponse> placeOrder(@Field("user_id")String cart_id,@Field("order_address")String order_address);

    @GET("getordered_items")
    Call<OrderedItemResponse> getOrderedItems(@Query("order_id")String order_id);

    @FormUrlEncoded
    @POST("update_cart")
    Call<CommonResponse> updateCartItem(@Field("cart_id")String cart_id,@Field("qty")String quantity);
//
    @FormUrlEncoded
    @POST("order_cancel")
    Call<CommonResponse> orderCancel(@Field("order_id")String order_id);




}
