package com.example.as_prm_thien.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.StudioViewHolder> {
    private final List<Studio> mList;
    private final Context mContext;
    private final IClickItemServiceListener iClickItemServiceListener;


    public CartAdapter(List<Studio> studioList, Context context, IClickItemServiceListener iClickItemServiceListener) {
        this.mContext = context;
        this.mList = studioList;
        this.iClickItemServiceListener = iClickItemServiceListener;
    }

    @NonNull
    @Override
    public CartAdapter.StudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);
        return new CartAdapter.StudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.StudioViewHolder holder, int position) {
        Studio studio = mList.get(position);
        if (studio == null) {
            return;
        }
        holder.ProductName.setText(studio.getName());
        holder.ProductPrice.setText(formatMoney(studio.getPrice()) + " VND");
        Picasso.get().load(studio.getAvatarStudio()).into(holder.ProductImage);
        holder.itemCount.setText("1");

        holder.itemCount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!holder.itemCount.getText().toString().isEmpty()) {
                    if (Integer.parseInt((String) holder.itemCount.getText().toString()) <= 0) {
                        holder.itemCount.setText("1");
                    }
                }
            }
        });

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt((String) holder.itemCount.getText().toString());
                value++;
                holder.itemCount.setText(value + "");
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = Integer.parseInt((String) holder.itemCount.getText().toString());
                if (value == 1) {
                    openConfirmAndDeleteDialog(Gravity.CENTER, position);
                } else {
                    value--;
                    holder.itemCount.setText(value + "");
                }
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openConfirmAndDeleteDialog(Gravity.CENTER, position);
            }
        });

        holder.productItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemServiceListener.onClickItemService(studio);
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

    public class StudioViewHolder extends RecyclerView.ViewHolder {
        LinearLayout productItem;
        Button add;
        Button minus;
        Button delete;
        EditText itemCount;
        TextView ProductName;
        TextView ProductPrice;
        ImageView ProductImage;


        public StudioViewHolder(@NonNull View itemView) {
            super(itemView);
            add = itemView.findViewById(R.id.add);
            minus = itemView.findViewById(R.id.minus);
            delete = itemView.findViewById(R.id.DeleteItem);
            itemCount = itemView.findViewById(R.id.itemCount);
            ProductName = itemView.findViewById(R.id.ProductName);
            ProductPrice = itemView.findViewById(R.id.ProductPrice);
            ProductImage = itemView.findViewById(R.id.ProductImage);
            ProductImage = itemView.findViewById(R.id.ProductImage);
            productItem = itemView.findViewById(R.id.productItem);
        }
    }
}