package com.example.as_prm_thien.Studio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
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
import androidx.viewpager.widget.ViewPager;

import com.example.as_prm_thien.Adapter.PhotoAdapter;
import com.example.as_prm_thien.Adapter.RecommendStudioAdapter;
import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import me.relex.circleindicator.CircleIndicator;

public class StudioActivity extends AppCompatActivity {
    private Studio studio;
    private ViewPager viewPager;

    private List<String> photoList;
    private Timer timer;
    private RecyclerView recyclerViewService;
    private RecommendStudioAdapter recommendStudioAdapter;
    private List<Studio> mStudioListRecommend;
    private Context context;
    private Button addToCardbtn;
    private Button buttonBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_page);
        context = getApplicationContext();
        //load Service Page Data (Studio)
        loadData();
        //LoadAppBar
        initToolBar();
        //Slide Image
        slideImage();
        //Auto SlideImages
        autoSlideImages();
        //load recommend service list
        getRecommendServicePack();

        //AddToCart
        buttonBooking = findViewById(R.id.btn_Booking);
        buttonBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, OrderDetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("studio", studio);
//                intent.putExtras(bundle);
//                startActivity(intent);
            }
        });
    }

    private void slideImage() {
        viewPager = findViewById(R.id.ViewPagerService);
        CircleIndicator circleIndicator = findViewById(R.id.Circle_Indicator_Service);
        photoList = getPhotoList();
        PhotoAdapter photoAdapter = new PhotoAdapter(this, photoList);
        viewPager.setAdapter(photoAdapter);
        circleIndicator.setViewPager(viewPager);
        photoAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    private void getRecommendServicePack() {
        List<Studio> servicePack = getData().stream().skip(0).limit(5).collect(Collectors.toList());
        recyclerViewService = findViewById(R.id.RecommendServiceRecyclerView);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewService.setLayoutManager(linearLayoutManager2);
        recommendStudioAdapter = new RecommendStudioAdapter(servicePack, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Studio studio) {
                goDetailService(studio);
            }
        });
        recyclerViewService.setAdapter(recommendStudioAdapter);
    }

    private List<Studio> getData() {
        List<Studio> mList = new ArrayList<>();
        mList.add(new Studio(1, "Artium", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts," +
                        " will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1000000, false));
        mList.add(new Studio(2, "Artium 2", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, " +
                        "will certainly be the right choice.", 5.0, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1000000, false));
        mList.add(new Studio(3, "Artium 3", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts," +
                        " will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1000000, false));
        return mList;
    }

    private void goDetailService(Studio studio) {
        Intent intent = new Intent(this, StudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void loadData() {
        if (getIntent().getExtras() == null) {
            Toast.makeText(context, "Load Content Error Null Object", Toast.LENGTH_SHORT).show();
            return;
        }
        studio = (Studio) getIntent().getExtras().get("studio");
        if (studio == null) {
            Toast.makeText(context, "Load Content Error Null Object", Toast.LENGTH_SHORT).show();
            return;
        } else {
            TextView serviceName = findViewById(R.id.ServiceNameDetail);
            serviceName.setText(studio.getName());
            TextView servicePrice = findViewById(R.id.ServicePriceDetail);
            servicePrice.setText("Price: " + formatMoney(studio.getPrice()) + " VND");
            TextView serviceDescription = findViewById(R.id.ServiceDescription);
            serviceDescription.setText(Html.fromHtml(studio.getDescription()));
        }
    }


    private String formatMoney(int Money) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(Money);
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    private List<String> getPhotoList() {
        List<String> myList = new ArrayList<>();
//        myList.add("https://i.imgur.com/DvpvklR.png");
//        myList.add("https://i.imgur.com/DvpvklR.png");
        myList.add(studio.getCoverImage());
        return myList;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void autoSlideImages() {
        if (photoList == null || photoList.isEmpty() || viewPager == null) {
            return;
        }
        if (timer == null) {
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler((Looper.getMainLooper())).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = photoList.size() - 1;
                        if (currentItem < totalItem) {
                            currentItem++;
                            viewPager.setCurrentItem(currentItem);
                        } else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 10000, 10000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}