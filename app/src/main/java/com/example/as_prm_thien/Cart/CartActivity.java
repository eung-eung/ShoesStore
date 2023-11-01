package com.example.as_prm_thien.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.CartAdapter;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Studio.StudioActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    CartAdapter cartAdapter;
    RecyclerView cartRecyclerView;
    List<Studio> studioList;
    Button btn_Booking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initToolBar();
        studioList = getData();

        cartRecyclerView = findViewById(R.id.CartRecyclerView);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        cartRecyclerView.setLayoutManager(linearLayoutManagerSort);
        cartAdapter = new CartAdapter(studioList, this, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Studio Studio) {
                Intent intent = new Intent(CartActivity.this, StudioActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("studio", Studio);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        cartRecyclerView.setAdapter(cartAdapter);

        btn_Booking = findViewById(R.id.CartBooking);
        btn_Booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, BookingProductActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("listProduct", (Serializable) getData());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.ToolBarCartActivity);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.valueOf(""));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}