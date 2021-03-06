package com.vingcoz.fantasia.home.ui.settings;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.core.SessionManager;
import com.vingcoz.fantasia.databinding.FragmentSettingsBinding;
import com.vingcoz.fantasia.home.ui.activity.AboutUsActivity;
import com.vingcoz.fantasia.home.ui.activity.ChangepasswordActivity;
import com.vingcoz.fantasia.home.ui.activity.OrderActivity;
import com.vingcoz.fantasia.login.LoginActivity;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    public FragmentSettingsBinding settingsBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false);

//        settingsBinding.layoutBase.textTitle.setText("Settings");
//
//
//        settingsBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
//        settingsBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
//            getActivity().onBackPressed();
//        });

        settingsBinding.textAbout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AboutUsActivity.class));
        });


        settingsBinding.textChangePassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangepasswordActivity.class));
        });

        settingsBinding.textMyOrders.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), OrderActivity.class));
        });

        settingsBinding.privacy.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://laundryhubqatar.com/mobile_app/privacypolicy/laundry_hub_privacy_policy.html"));
            startActivity(browserIntent);
        });

        settingsBinding.update.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vingcoz.laundryapp"));
            startActivity(browserIntent);
        });

        settingsBinding.logout.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
            alertDialog.setTitle(getActivity().getString(R.string.logout));
            alertDialog.setTitle(getActivity().getString(R.string.logout_message));

            alertDialog.setPositiveButton(getActivity().getString(R.string.yes), (dialog, which) -> {
                SessionManager.getSessionInstance(getActivity()).clearUserCredentials();
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                loginIntent.setFlags((FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK));
                dialog.cancel();
                startActivity(loginIntent);
            });
            alertDialog.setNegativeButton(getActivity().getString(R.string.no), (dialog, which) -> dialog.cancel());
            alertDialog.show();
        });
        return settingsBinding.getRoot();
    }
}