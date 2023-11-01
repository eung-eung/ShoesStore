package com.example.as_prm_thien.Studio;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.as_prm_thien.Adapter.StudioAdapter;
import com.example.as_prm_thien.IClickInterface.IClickItemServiceListener;
import com.example.as_prm_thien.Model.Studio;
import com.example.as_prm_thien.R;

import java.util.ArrayList;
import java.util.List;


public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView searchView;
    private RecyclerView recyclerViewStudio;
    private StudioAdapter studioAdapter;
    private List<Studio> mListStudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar = findViewById(R.id.myToolBarSearch);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Studio");
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.item_color_appbar));

        //studioList
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudioSearch);
        mListStudio = getData();
        studioAdapter = new StudioAdapter(mListStudio, new IClickItemServiceListener() {
            @Override
            public void onClickItemService(Studio Studio) {
                onClickItemGoDetail(Studio);
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerViewStudio.setLayoutManager(gridLayoutManager);
        recyclerViewStudio.setAdapter(studioAdapter);
        recyclerViewStudio.setVisibility(View.GONE);


    }

    private List<Studio> getData() {
        List<Studio> mList = new ArrayList<>();
        mList.add(new Studio(1, "Artium", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1400000, false));
        mList.add(new Studio(2, "Artium 2", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 5.0, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1600000, false));
        mList.add(new Studio(3, "Artium 3", "District 9",
                "With the criteria of choosing a spacious, unique, cozy space and many different contexts, will certainly be the right choice.", 4.5, "2023-10-14T17:00:00.000+00:00",
                "https://sm.ign.com/ign_nordic/cover/a/avatar-gen/avatar-generations_prsz.jpg",
                "https://variety.com/wp-content/uploads/2023/06/avatar-1.jpg", 1700000, false));
        return mList;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar_search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        EditText txtSearch = (EditText) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        txtSearch.setTextColor(Color.BLACK);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                studioAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewStudio.setVisibility(View.VISIBLE);
                studioAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    private void onClickItemGoDetail(Studio studio) {
//        Intent intent = new Intent(this, StudioActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("studio", studio);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }
}