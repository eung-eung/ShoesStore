package com.example.as_prm_thien.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.OrderDetailAdapter;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.Model.Order;
import com.example.as_prm_thien.R;

import java.text.NumberFormat;
import java.util.Locale;


public class OrderDetailActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderDetailAdapter orderDetailAdapter;
    private Order order;

    public static int customRound(double number) {
        double fractionalPart = number - (int) number;

        if (fractionalPart >= 0.5) {
            return (int) Math.round(number);
        } else {
            return (int) Math.floor(number);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        getData();
        initToolBar();
        initData();

        recyclerView = findViewById(R.id.RecyclerViewOrderDetail);

        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManagerSort);
        orderDetailAdapter = new OrderDetailAdapter(order.getOrderDetail());
        recyclerView.setAdapter(orderDetailAdapter);

    }

    private void initData() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);

        TextView bookingPrice = findViewById(R.id.tv_OrderDetail_BookingPrice);
        TextView fees = findViewById(R.id.tv_OrderDetail_FeesPrice);
        TextView totalPrice = findViewById(R.id.tv_OrderDetail_TotalPrice);

        int _totalPrice = 0;
        for (int i = 0; i < order.getOrderDetail().size(); i++) {
            _totalPrice = _totalPrice + order.getOrderDetail().get(i).getPrice();
        }

        bookingPrice.setText(numberFormat.format(_totalPrice) + "VND");

        int roundedPrice = customRound(_totalPrice * 0.05);
        fees.setText(numberFormat.format(roundedPrice) + "VND");
        totalPrice.setText(numberFormat.format(roundedPrice + _totalPrice) + "VND");
    }

    private void getData() {
        if (getIntent().getExtras() != null) {
            order = (Order) getIntent().getExtras().get("order");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.OrderDetailToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(String.valueOf("OrderDetail " + order.getOrderId()));
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