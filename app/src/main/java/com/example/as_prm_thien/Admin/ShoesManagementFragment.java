package com.example.as_prm_thien.Admin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.as_prm_thien.Adapter.CartAdapter;
import com.example.as_prm_thien.Cart.BookingProductActivity;
import com.example.as_prm_thien.Cart.CartActivity;
import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Studio.StudioActivity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ShoesManagementFragment extends Fragment {
    ShoesManagementAdapter shoesAdapter;
    RecyclerView shoesManagementRecyclerView;
    List<Studio> shoesList;
    Button addNewShoes;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        shoesList = getData();

        shoesManagementRecyclerView = view.findViewById(R.id.shoesManagementRecyclerView);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        shoesManagementRecyclerView.setLayoutManager(linearLayoutManagerSort);
        shoesAdapter = new ShoesManagementAdapter(shoesList, getContext(), new IClickItemShoesManagementListener() {
            @Override
            public void onClickItemShoesManagement(Studio studio) {
                openUpdateShoesDialog(Gravity.CENTER, studio, view);
            }
        });


        shoesManagementRecyclerView.setAdapter(shoesAdapter);

        addNewShoes = view.findViewById(R.id.addNewShoes);
        addNewShoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(CartActivity.this, BookingProductActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("listProduct", (Serializable) getData());
//                intent.putExtras(bundle);
//                startActivity(intent);

                openAddShoesDialog(Gravity.CENTER, view);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoes_management, container, false);
    }


    private List<Studio> getData() {
        List<Studio> mList = new ArrayList<>();
        mList.add(new Studio(1, "Artium", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1000000, false));
        mList.add(new Studio(2, "Artium 2", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 5.0, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1200000, false));
        mList.add(new Studio(3, "Artium 3", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1300000, false));
        return mList;
    }

    private void openUpdateShoesDialog(int gravity, Studio shoes, View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update_shoes);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        EditText nameObject = dialog.findViewById(R.id.nameShoes_update);
        EditText priceObject = dialog.findViewById(R.id.priceShoes_update);
        EditText descriptionObject = dialog.findViewById(R.id.descriptionShoes_update);
        EditText imageUrlObject = dialog.findViewById(R.id.imageUrl_update);

        nameObject.setText(shoes.getName());
        priceObject.setText(shoes.getPrice() + "");
        descriptionObject.setText(shoes.getDescription());
        imageUrlObject.setText(shoes.getAvatarStudio());

        Button updateObject = dialog.findViewById(R.id.btn_Update_shoes);
        updateObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameObject.getText().toString();
                String price = priceObject.getText().toString();
                String description = descriptionObject.getText().toString();
                String imageUrl = imageUrlObject.getText().toString();

//                Studio newShoes = new Studio(1, name,shoes.getAddress(),
//                        description,shoes.getTotalRating(),
//                        shoes.getCreateDate(),imageUrl, imageUrl,Integer.parseInt(price),shoes.getDeleted());
                if (name.isEmpty() || price.isEmpty() || description.isEmpty() || imageUrl.isEmpty()) {
                    Toast.makeText(v.getContext(), "Update Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
                    shoes.setName(name);
                    shoes.setPrice(Integer.parseInt(price));
                    shoes.setDescription(description);
                    shoes.setAvatarStudio(imageUrl);
                    Toast.makeText(v.getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
                    shoesAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        Button closeBtn = dialog.findViewById(R.id.btn_CancelUpdate_shoes);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openAddShoesDialog(int gravity,View v) {
        final Dialog dialog = new Dialog(v.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_update_shoes);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        EditText nameObject = dialog.findViewById(R.id.nameShoes_update);
        EditText priceObject = dialog.findViewById(R.id.priceShoes_update);
        EditText descriptionObject = dialog.findViewById(R.id.descriptionShoes_update);
        EditText imageUrlObject = dialog.findViewById(R.id.imageUrl_update);

        Button updateObject = dialog.findViewById(R.id.btn_Update_shoes);
        updateObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameObject.getText().toString();
                String price = priceObject.getText().toString();
                String description = descriptionObject.getText().toString();
                String imageUrl = imageUrlObject.getText().toString();


                if (name.isEmpty() || price.isEmpty() || description.isEmpty() || imageUrl.isEmpty()) {
                    Toast.makeText(v.getContext(), "Create Unsuccessful", Toast.LENGTH_SHORT).show();
                } else {
//                    POST new shoes API ở đây
                    Studio shoes = new Studio(1, "Artium", "District 9",
                            "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                            "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                            "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1000000, false);

                    Studio newShoes = new Studio(1, name, shoes.getAddress(),
                            description, shoes.getTotalRating(),shoes.getCreateDate(),imageUrl,
                            shoes.getCoverImage(),Integer.parseInt(price),shoes.getDeleted());
                    Toast.makeText(v.getContext(), "Create Successful", Toast.LENGTH_SHORT).show();
                    shoesList.add(newShoes);
                    shoesAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }

            }
        });
        Button closeBtn = dialog.findViewById(R.id.btn_CancelUpdate_shoes);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}