package com.vingcoz.fantasia.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.CategoriesAdapter;
import com.vingcoz.fantasia.adapter.FeaturedProductAdapter;
import com.vingcoz.fantasia.adapter.ImageSliderAdapter;
import com.vingcoz.fantasia.databinding.FragmentHomeBinding;
import com.vingcoz.fantasia.pojo.Categories;
import com.vingcoz.fantasia.pojo.FeaturedProducts;
import com.vingcoz.fantasia.util.Constants;
import com.vingcoz.fantasia.util.NetworkUtilities;
import com.vingcoz.fantasia.viewmodel.BannerViewModel;
import com.vingcoz.fantasia.viewmodel.CategoryViewModel;
import com.vingcoz.fantasia.viewmodel.FeaturedProductViewModel;
import com.vingcoz.fantasia.viewmodel.OffersViewModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    public FragmentHomeBinding homeBinding;
    public CategoryViewModel categoryViewModel;
    public CategoriesAdapter categoriesAdapter;
    public OffersViewModel offersViewModel;
    public FeaturedProductViewModel featuredProductViewModel;
    public FeaturedProductAdapter featuredProductAdapter;
    private HomeViewModel homeViewModel;
    public BannerViewModel bannerViewModel;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        categoryViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(CategoryViewModel.class);
        bannerViewModel=ViewModelProviders.of((FragmentActivity)this.getActivity()).get(BannerViewModel.class);
        offersViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(OffersViewModel.class);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
//




        homeBinding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerCategories.setHasFixedSize(true);


        homeBinding.recyclerFeaturedProducts.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerCategories.setHasFixedSize(true);

        homeBinding.swipe.setOnRefreshListener(() -> {
            if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
                setValuesToFields();
                getCategories();
                getfeaturedProduct();
                homeBinding.swipe.setRefreshing(false);
            }else {
                Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
            }
        });

        setValuesToFields();

        getCategories();

        getfeaturedProduct();

        return homeBinding.getRoot();

    }
    private void setValuesToFields() {
        //banner image

        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            bannerViewModel.getBanners().observe(getActivity(),bannerResponse -> {
                // List<BannerResponse.Banners> bannerList = new ArrayList<>();

                homeBinding.rlBanner.setVisibility(View.VISIBLE);
                homeBinding.vpImage.setAdapter(new ImageSliderAdapter(getActivity(), bannerResponse.getBanners()));

                //homeBinding.cpImage.setViewPager(homeBinding.vpImage);

                final float density = getResources().getDisplayMetrics().density;

                //Set circle indicator radius
                //homeBinding.cpImage.setRadius(5 * density);

                homeBinding.vpImage.startAutoScroll();
                homeBinding.vpImage.setInterval(5000);
                homeBinding.vpImage.setCycle(true);
                homeBinding.vpImage.setStopScrollWhenTouch(true);

                // Pager listener over indicator
//               // homeBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//                    @Override
//                    public void onPageSelected(int position) {
//
//                    }
//
//                    @Override
//                    public void onPageScrolled(int pos, float arg1, int arg2) {
//
//                    }
//
//                    @Override
//                    public void onPageScrollStateChanged(int pos) {
//
//                    }
//                });
            });

        }
        else{
            Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
        }

    }
    public void getCategories(){
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            categoryViewModel.getCategories().observe(getActivity(),categoryResponse -> {
                if(categoryResponse !=null && categoryResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    categoriesAdapter=new CategoriesAdapter(getActivity(),categoryResponse.getCategories());
                    homeBinding.recyclerCategories.setAdapter(categoriesAdapter);
                }
            });
        }
        else{
            Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
        }
    }

    public void getfeaturedProduct(){
        if (NetworkUtilities.getNetworkInstance(getActivity()).isConnectedToInternet()){
            offersViewModel.getOffers().observe(this.getActivity(),offerResponse -> {
                if (offerResponse !=null && offerResponse.getStatus().equals(Constants.SERVER_RESPONSE_SUCCESS)){
                    featuredProductAdapter=new FeaturedProductAdapter(getActivity(),offerResponse.getSpecial_offer_items());
                    homeBinding.recyclerFeaturedProducts.setAdapter(featuredProductAdapter);
                }
            });
        }
        else{
            Toast.makeText(getActivity(),"Internet Connection not available",Toast.LENGTH_LONG).show();
        }
    }

}