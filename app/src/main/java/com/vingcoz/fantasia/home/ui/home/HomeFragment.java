package com.vingcoz.fantasia.home.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
       // offersViewModel=ViewModelProviders.of((FragmentActivity)this.getActivity()).get(OffersViewModel.class);
        featuredProductViewModel=ViewModelProviders.of((FragmentActivity)this.getActivity()).get(FeaturedProductViewModel.class);

    }


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
//




        homeBinding.recyclerCategories.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerCategories.setHasFixedSize(true);


        homeBinding.recyclerFeaturedProducts.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        homeBinding.recyclerCategories.setHasFixedSize(true);

        setValuesToFields();

        getCategories();

        getfeaturedProduct();

        return homeBinding.getRoot();

    }
    private void setValuesToFields() {
        //banner image
        List<String> bannerList = new ArrayList<>();
        bannerList.add("1");
        bannerList.add("2");
        bannerList.add("3");
        homeBinding.rlBanner.setVisibility(View.VISIBLE);
        homeBinding.vpImage.setAdapter(new ImageSliderAdapter(getActivity(), bannerList));

        homeBinding.cpImage.setViewPager(homeBinding.vpImage);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        homeBinding.cpImage.setRadius(5 * density);

        homeBinding.vpImage.startAutoScroll();
        homeBinding.vpImage.setInterval(5000);
        homeBinding.vpImage.setCycle(true);
        homeBinding.vpImage.setStopScrollWhenTouch(true);

        // Pager listener over indicator
        homeBinding.cpImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }
    public void getCategories(){
        categoryViewModel.getCategories().observe((LifecycleOwner) this.getActivity(), new Observer<List<Categories>>() {
            @Override
            public void onChanged(List<Categories> categories) {
                categoriesAdapter=new CategoriesAdapter(getActivity(),categories);
                homeBinding.recyclerCategories.setAdapter(categoriesAdapter);
            }
        });
    }

    public void getfeaturedProduct(){
        featuredProductViewModel.getFeaturedProducts().observe((LifecycleOwner) this.getActivity(), new Observer<List<FeaturedProducts>>() {
            @Override
            public void onChanged(List<FeaturedProducts> featuredProducts) {
                featuredProductAdapter=new FeaturedProductAdapter(getActivity(),featuredProducts);
                homeBinding.recyclerFeaturedProducts.setAdapter(featuredProductAdapter);
            }
        });
    }

}