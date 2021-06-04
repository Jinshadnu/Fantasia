package com.vingcoz.fantasia.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.vingcoz.fantasia.R;
import com.vingcoz.fantasia.databinding.LayoutShipaddressBinding;
import com.vingcoz.fantasia.pojo.AddressResponse;

import java.util.List;

import static android.view.LayoutInflater.from;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {
    public Context context;
    public List<AddressResponse.Address> addressList;

    public AddressAdapter(Context context, List<AddressResponse.Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutShipaddressBinding addressBinding= DataBindingUtil.inflate(from(context), R.layout.layout_shipaddress,parent,false);
        return new AddressViewHolder(addressBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        AddressResponse.Address address=addressList.get(position);
        holder.addressBinding.setAddress(address);


//        holder.categoriesBinding.cardViewServices.setOnClickListener(view -> {
//            Intent intent=new Intent(context.getApplicationContext(), ItemActivity.class);
//            String category_id=categoriesList.get(position).getCategory_id();
//            intent.putExtra("category_id",category_id);
//            context.startActivity(intent);
//        });

    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        public LayoutShipaddressBinding addressBinding;
        public AddressViewHolder(@NonNull LayoutShipaddressBinding layoutAddressBinding) {
            super(layoutAddressBinding.getRoot());
            this.addressBinding=layoutAddressBinding;
        }
    }
}
