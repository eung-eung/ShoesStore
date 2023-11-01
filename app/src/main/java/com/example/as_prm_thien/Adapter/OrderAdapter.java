package com.example.as_prm_thien.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.IClickInterface.IClickItemOrderListener;
import com.example.as_prm_thien.Model.Order;
import com.example.as_prm_thien.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private final List<Order> mList;
    private final Context context;
    private final IClickItemOrderListener iClickItemOrderListenerListener;


    public OrderAdapter(List<Order> mListOrder, Context context, IClickItemOrderListener iClickItemOrderListenerListener) {
        this.mList = mListOrder;
        this.context = context;
        this.iClickItemOrderListenerListener = iClickItemOrderListenerListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_scheduled_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = mList.get(position);

        if (order == null) {
            return;
        }

        holder.orderDate.setText("Date: " + order.getOrderDate());
        holder.serviceName.setText("Order Id: " + order.getOrderId());
        Picasso.get()
                .load(order.getOrderDetail().get(0).getAvatarStudio())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.urlImageService);


        holder.btn_ViewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemOrderListenerListener.onClickItemOrder(order);
            }
        });

        holder.btn_CanceledOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog(order.getOrderId(), position);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    void confirmDialog(int orderId, int index) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Cancel order " + orderId + " ?");
        builder.setMessage("Are you sure you want to cancel " + orderId + " ?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cancelOrderAction(orderId, index);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    private void cancelOrderAction(int orderId, int i) {
        Toast.makeText(context, "Cancel Successful", Toast.LENGTH_SHORT).show();
        mList.remove(i);
        notifyDataSetChanged();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView orderDate;
        public ImageView urlImageService;
        public TextView serviceName;
        public Button btn_ViewOrder;
        public Button btn_CanceledOrder;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.orderServiceName);
            orderDate = itemView.findViewById(R.id.orderDate);
            urlImageService = itemView.findViewById(R.id.urlImageService);
            btn_ViewOrder = itemView.findViewById(R.id.btn_ViewOrder);
            btn_CanceledOrder = itemView.findViewById(R.id.btn_CanceledOrder);
        }
    }

}
