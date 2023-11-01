package com.example.as_prm_thien.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.StudioAdapter;
import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;
import com.example.as_prm_thien.Studio.SearchActivity;
import com.example.as_prm_thien.Studio.StudioActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewService;
    private StudioAdapter studioAdapter;
    private List<Studio> mStudioList;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        sortItemData(view);
        studioItemData(view);
        mStudioList = getData();
        studioAdapter = new StudioAdapter(mStudioList, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Studio studio) {
                goStudioDetail(studio);
            }
        });
        recyclerViewService.setAdapter(studioAdapter);
        LinearLayout btn_SearchHomeFragment = view.findViewById(R.id.btn_SearchHomeFragment);
        btn_SearchHomeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
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

    private void studioItemData(@NonNull View view) {
        recyclerViewService = view.findViewById(R.id.RecyclerViewService);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerViewService.setLayoutManager(gridLayoutManager);
    }

//    private void sortItemData(@NonNull View view) {
//        RecyclerView recyclerViewSort = view.findViewById(R.id.RecyclerSort);
//        LinearLayoutManager linearLayoutManagerSort = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
//        recyclerViewSort.setLayoutManager(linearLayoutManagerSort);
//        SortHomeAdapter sortHomeAdapter = new SortHomeAdapter(getSortData(), new IClickItemSortListener() {
//            @Override
//            public void onClickItemSort(String sortBy) {
//                studioAdapter.getFilter().filter("@!" + sortBy);
//                Log.d("eqw", "@!" + sortBy);
//            }
//        });
//        recyclerViewSort.setAdapter(sortHomeAdapter);
//    }

    private void goStudioDetail(Studio studio) {
        Intent intent = new Intent(getActivity(), StudioActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private List<String> getSortData() {
        String[] sortList = {"Top Rating"};
        List<String> myList = new ArrayList<>();
        Collections.addAll(myList, sortList);
        return myList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);
        return view;
    }
}