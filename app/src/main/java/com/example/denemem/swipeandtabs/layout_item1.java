package com.example.denemem.swipeandtabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.denemem.swipeandtabs.Adapter.SearchAdapter;
import com.example.denemem.swipeandtabs.DataBase.DataBase;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class layout_item1 extends FragmentActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    List<String> suggestList = new ArrayList<>();


    DataBase dataBase;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_items);

        //init View
        recyclerView = (RecyclerView) findViewById(R.id.reycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //init DB
        dataBase = new DataBase(this);

        loadSuggestList();

        adapter = new SearchAdapter(this,dataBase.getFriends());
        recyclerView.setAdapter(adapter);

    }


    private void loadSuggestList() {
        suggestList = dataBase.getNames();
    }



}
