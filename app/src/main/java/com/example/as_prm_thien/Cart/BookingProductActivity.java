package com.example.as_prm_thien.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.OrderDetailAdapter;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.Model.OrderDetail;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class BookingProductActivity extends AppCompatActivity {
    private Button btn_Continue;
    private Button btn_Cancel;
    private List<Studio> studioList;
    private List<OrderDetail> orderDetail;
    private Toolbar toolbar;

    private OrderDetailAdapter orderDetailAdapter;
    private RecyclerView orderDetailRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_product);

        initToolBar();
        initOrderDetailData();
        initData();

        btn_Continue = findViewById(R.id.btn_ContinuePayment);
        btn_Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookingProductActivity.this, "continue", Toast.LENGTH_SHORT).show();
            }
        });

        btn_Cancel = findViewById(R.id.btn_CancelBooking);
        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), HomeActivity.class);
                startActivity(intent);
            }
        });

        orderDetailRecyclerView = findViewById(R.id.RecyclerViewOrderDetailBooking);
        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        orderDetailRecyclerView.setLayoutManager(linearLayoutManagerSort);
        orderDetailAdapter = new OrderDetailAdapter(orderDetail);
        orderDetailRecyclerView.setAdapter(orderDetailAdapter);

    }

    private void initOrderDetailData() {
        if (getIntent().getExtras() != null) {
            studioList = (List<Studio>) getIntent().getExtras().get("listProduct");
        }
        orderDetail = new ArrayList<>();
        for (int i = 0; i < studioList.size(); i++) {
            Studio studio = studioList.get(i);
            orderDetail.add(new OrderDetail(studio.getStudioId(), studio.getPrice(), studio.getName(),
                    1, studio.getAvatarStudio(), studio.getCoverImage()));
        }
    }

    private void initData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        TextView bookingPrice = findViewById(R.id.tv_BookingPrice);
        TextView fees = findViewById(R.id.tv_FeesPrice);
        TextView totalPrice = findViewById(R.id.tv_TotalPrice);
        int _totalPrice = 0;
        for (int i = 0; i < orderDetail.size(); i++) {
            _totalPrice = _totalPrice + (orderDetail.get(i).getPrice() * orderDetail.get(i).getItemCount());
        }
        bookingPrice.setText(numberFormat.format(_totalPrice) + " VND");

        int roundedPrice = (int) (_totalPrice * 0.05);
        fees.setText(numberFormat.format(roundedPrice) + " VND");
        totalPrice.setText(numberFormat.format(roundedPrice + _totalPrice) + " VND");
    }

    private void initToolBar() {
        toolbar = findViewById(R.id.OrderBookingToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Order Detail Booking");
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}