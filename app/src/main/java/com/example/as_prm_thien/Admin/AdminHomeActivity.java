package com.example.as_prm_thien.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.as_prm_thien.Cart.CartActivity;
import com.example.as_prm_thien.Fragment.BookingFragment;
import com.example.as_prm_thien.Fragment.ChatFragment;
import com.example.as_prm_thien.Fragment.HomeFragment;
import com.example.as_prm_thien.Fragment.NotificationActivity;
import com.example.as_prm_thien.Fragment.UserFragment;
import com.example.as_prm_thien.Home.HomeActivity;
import com.example.as_prm_thien.MainActivity;
import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Studio.SearchActivity;

public class AdminHomeActivity extends AppCompatActivity {
    private Fragment selectedFragment = null;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        loadBottomNavigationView();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.logout) {
            LogoutAccount();
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LogoutAccount() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void loadBottomNavigationView() {
        AHBottomNavigation bottomNavigationView = findViewById(R.id.bottomNavigationView_admin);
        //Define Items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Dashboard", R.drawable.home_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Shoes", R.drawable.chat_white_48dp, R.color.ToolBar);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Orders", R.drawable.shopping_cart_white_48dp, R.color.ToolBar);

        // Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);


        bottomNavigationView.setCurrentItem(0);
        //Style
        bottomNavigationView.setColored(false);
        // Change colors
        bottomNavigationView.setAccentColor(Color.parseColor("#235a8f"));
        bottomNavigationView.setInactiveColor(Color.parseColor("#cfcfcf"));

        //OnClickItem
        bottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0) {
                    selectedFragment = new DashboardFragment();
                    getSupportActionBar().setTitle("Dashboard");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    getSupportActionBar();
                }
                if (position == 1) {
                    getSupportActionBar().setTitle("Shoes");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    selectedFragment = new ShoesManagementFragment();
                }
                if (position == 2) {
                    getSupportActionBar().setTitle("Orders");
                    getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
                    selectedFragment = new OrderManagementFragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, selectedFragment).commit();
                }
                return true;
            }
        });
        selectedFragment = new DashboardFragment();
        getSupportActionBar().setTitle("Shoes");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));
        getSupportActionBar();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container_admin, selectedFragment).commit();
    }
}