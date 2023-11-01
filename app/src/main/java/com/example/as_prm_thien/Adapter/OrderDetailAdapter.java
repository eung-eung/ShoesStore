package com.example.as_prm_thien.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Model.OrderDetail;
import com.example.as_prm_thien.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderViewHolder> {
    private final List<OrderDetail> mList;


    public OrderDetailAdapter(List<OrderDetail> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_detail_item, parent, false);
        return new OrderDetailAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderViewHolder holder, int position) {
        OrderDetail orderDetail = mList.get(position);
        if (orderDetail == null) {
            return;
        }
        holder.ProductName.setText(orderDetail.getProductName());
        holder.ProductPrice.setText(formatMoney(orderDetail.getPrice()) + " VND");
        Picasso.get().load(orderDetail.getAvatarStudio()).into(holder.ProductImage);
        holder.itemCount.setText("items: " + orderDetail.getItemCount());

    }

    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView itemCount;
        TextView ProductName;
        TextView ProductPrice;
        ImageView ProductImage;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCount = itemView.findViewById(R.id.itemCount_Booking);
            ProductName = itemView.findViewById(R.id.ProductName_Booking);
            ProductPrice = itemView.findViewById(R.id.ProductPrice_Booking);
            ProductImage = itemView.findViewById(R.id.ProductImage_Booking);
            ProductImage = itemView.findViewById(R.id.ProductImage_Booking);
        }
    }
}