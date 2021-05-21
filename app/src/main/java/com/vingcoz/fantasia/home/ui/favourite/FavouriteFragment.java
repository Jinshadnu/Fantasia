package com.vingcoz.fantasia.home.ui.favourite;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.adapter.WishlistAdapter;
import com.vingcoz.fantasia.databinding.FragmentFavouriteBinding;
import com.vingcoz.fantasia.pojo.Wishlist;
import com.vingcoz.fantasia.viewmodel.WishlistViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FavouriteFragment extends Fragment {
    public WishlistViewModel wishlistViewModel;
    public WishlistAdapter wishlistAdapter;
    public FragmentFavouriteBinding favouriteBinding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        wishlistViewModel= ViewModelProviders.of((FragmentActivity)this.getActivity()).get(WishlistViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        favouriteBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_favourite, container, false);

        favouriteBinding.recyclerFavourites.setLayoutManager(new LinearLayoutManager(getActivity()));
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
//                DividerItemDecoration.VERTICAL);
//        favouriteBinding.recyclerFavourites.addItemDecoration(dividerItemDecoration);
        favouriteBinding.recyclerFavourites.setHasFixedSize(true);

//        favouriteBinding.layoutBase.textTitle.setText("My Wishlist");
//
//        favouriteBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
//        favouriteBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
//            getActivity().onBackPressed();
//        });

        getWishlist();

        return  favouriteBinding.getRoot();


    }

    public void getWishlist(){
        wishlistViewModel.getWishlist().observe((LifecycleOwner) this.getActivity(), new Observer<List<Wishlist>>() {
            @Override
            public void onChanged(List<Wishlist> wishlists) {
                wishlistAdapter=new WishlistAdapter(getActivity(),wishlists);
                favouriteBinding.recyclerFavourites.setAdapter(wishlistAdapter);
            }
        });
    }
}