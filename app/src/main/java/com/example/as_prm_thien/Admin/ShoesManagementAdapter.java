package com.example.as_prm_thien.Admin;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ShoesManagementAdapter extends RecyclerView.Adapter<ShoesManagementAdapter.ShoesManagementViewHolder> {
    private final List<Studio> mList;
    private final Context mContext;
    private final IClickItemShoesManagementListener iClickItemShoesManagementListener;


    public ShoesManagementAdapter(List<Studio> mList, Context mContext, IClickItemShoesManagementListener iClickItemShoesManagementListener) {
        this.mList = mList;
        this.mContext = mContext;
        this.iClickItemShoesManagementListener = iClickItemShoesManagementListener;
    }

    @NonNull
    @Override
    public ShoesManagementAdapter.ShoesManagementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shoes_item_admin, parent, false);
        return new ShoesManagementAdapter.ShoesManagementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoesManagementAdapter.ShoesManagementViewHolder holder, int position) {
        Studio studio = mList.get(position);
        if (studio == null) {
            return;
        }
        holder.ProductName.setText(studio.getName());
        holder.ProductPrice.setText(formatMoney(studio.getPrice()) + " VND");
        Picasso.get().load(studio.getAvatarStudio()).into(holder.ProductImage);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfirmAndDeleteDialog(Gravity.CENTER, position);
            }
        });

        holder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemShoesManagementListener.onClickItemShoesManagement(studio);
            }
        });
    }

    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }

    private void openConfirmAndDeleteDialog(int gravity, int studioIndex) {
        final Dialog dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_confirm);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        Button noBtn = dialog.findViewById(R.id.noButton);
        noBtn.setOnClickListener(view -> {
            dialog.dismiss();
        });

        Button yesBtn = dialog.findViewById(R.id.yesButton);
        yesBtn.setOnClickListener(view -> {
            removeItem(studioIndex);
            dialog.dismiss();
        });
        dialog.show();
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public void removeItem(int index) {
        mList.remove(index);
        notifyItemRemoved(index);
        notifyDataSetChanged();
    }

    public class ShoesManagementViewHolder extends RecyclerView.ViewHolder {
        LinearLayout productItem;
        Button delete;
        TextView ProductName;
        TextView ProductPrice;
        ImageView ProductImage;


        public ShoesManagementViewHolder(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.DeleteItem_admin);
            ProductName = itemView.findViewById(R.id.ProductName_admin);
            ProductPrice = itemView.findViewById(R.id.ProductPrice_admin);
            ProductImage = itemView.findViewById(R.id.ProductImage_admin);
            productItem = itemView.findViewById(R.id.productItem_admin);
        }
    }
}