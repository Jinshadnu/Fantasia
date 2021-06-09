package com.vingcoz.fantasia.home.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.FragmentProfileBinding;
import com.vingcoz.fantasia.home.ui.activity.AddAddressActivity;
import com.vingcoz.fantasia.home.ui.activity.AddressActivity;
import com.vingcoz.fantasia.home.ui.activity.ChangepasswordActivity;
import com.vingcoz.fantasia.home.ui.activity.EditProfileActivity;
import com.vingcoz.fantasia.home.ui.activity.ForgotPasswordActivity;
import com.vingcoz.fantasia.home.ui.activity.MyOrderActivity;
import com.vingcoz.fantasia.home.ui.activity.OrderActivity;
import com.vingcoz.fantasia.login.LoginActivity;
import com.vingcoz.fantasia.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    public FragmentProfileBinding profileBinding;
    public EditText editText_phone,editText_email;
    public String userphone,useremail;
    public String user_id;
    public String username,phone,email;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        profileBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constants.MyPREFERENCES, Context.MODE_PRIVATE);
        user_id=sharedPreferences.getString(Constants.USER_ID,user_id);
        userphone=sharedPreferences.getString(Constants.PHONE,null);
        useremail=sharedPreferences.getString(Constants.EMAIL,null);
        username=sharedPreferences.getString(Constants.USER_NAME,null);

        profileBinding.textView5.setText(userphone);
        profileBinding.textView6.setText(useremail);
        profileBinding.textUsername.setText(username);




        profileBinding.textAddress.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddAddressActivity.class));
        });
        profileBinding.textHistory.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), OrderActivity.class));
        });

        profileBinding.textChangepassword.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), ChangepasswordActivity.class));
        });

//        profileBinding.textForgetpassword.setOnClickListener(v -> {
//            startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
//        });

        profileBinding.textLogout.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });

        profileBinding.textEditprofile.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), EditProfileActivity.class));
        });
//        profileBinding.layoutBase.textTitle.setText("Profile");
//
//        profileBinding.layoutBase.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
//        profileBinding.layoutBase.toolbar.setNavigationOnClickListener(v -> {
//            getActivity().onBackPressed();
//        });
        return profileBinding.getRoot();

    }
    public void withEditText(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Profile");
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.layout_edit_profile, null);
        editText_phone=view.findViewById(R.id.editText_phone);
        editText_email=view.findViewById(R.id.editTextEmail);
        builder.setView(view);


        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Toast.makeText(cogetApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();



            }
        });




        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                // Toast.makeText(cogetApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}