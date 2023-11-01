package com.example.as_prm_thien.Booking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.OrderAdapter;
import com.example.as_prm_thien.IClickInterface.IClickItemOrderListener;
import com.example.as_prm_thien.Model.Order;
import com.example.as_prm_thien.Model.OrderDetail;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;

import java.util.ArrayList;
import java.util.List;


public class CanceledFragment extends Fragment {
    private RecyclerView recyclerViewOrder;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;
    private List<OrderDetail> orderDetailList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData(view);
    }

    private void loadData(@NonNull View view) {
//        orderList = getData().stream().filter(p->p.getStatus().equals("cancel")).collect(Collectors.toList());
        orderDetailList = new ArrayList<>();
        orderList = new ArrayList<>();

        List<Studio> studioList = getData();
        for (int i = 0; i < studioList.size(); i++) {
            Studio studio = studioList.get(i);
            orderDetailList.add(new OrderDetail(studio.getStudioId(), studio.getPrice(), studio.getName(),
                    1, studio.getAvatarStudio(), studio.getCoverImage()));
        }
        orderList.add(new Order(1, "2023-10-13", orderDetailList));
        orderList.add(new Order(2, "2023-10-13", orderDetailList));

        loadBookingData(view, orderList);
    }


    private void loadBookingData(@NonNull View view, List<Order> value) {
        recyclerViewOrder = view.findViewById(R.id.orderCanceledRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);
        orderAdapter = new OrderAdapter(value, getContext(), new IClickItemOrderListener() {
            @Override
            public void onClickItemOrder(Order order) {
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order", order);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        recyclerViewOrder.setAdapter(orderAdapter);
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_canceled, container, false);
        return view;
    }
}