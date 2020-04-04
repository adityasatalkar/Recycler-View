package com.droidbyme.recyclerviewselection.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.adapter.DataAdapter;
import com.droidbyme.recyclerviewselection.model.Data;
import com.droidbyme.recyclerviewselection.model.Planet;
import com.droidbyme.recyclerviewselection.model.Statewise;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class NormalRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<Planet> planetArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recycler View");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        planetArrayList = new ArrayList<>();
        adapter = new DataAdapter(this, planetArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        try {
            createListData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createListData() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = ApiCall.getDataFromApi(ApiCall.DATA_URL);
        Data data = gson.fromJson(jsonString, Data.class);
        List<Statewise> statewiseList = data.getStatewise();
        for (Statewise stateWiseObject: statewiseList) {
            Planet planet = new Planet(stateWiseObject.getState(),Integer.parseInt(stateWiseObject.getConfirmed()),Integer.parseInt(stateWiseObject.getActive()), Integer.parseInt(stateWiseObject.getRecovered()),Integer.parseInt(stateWiseObject.getDeaths()));
            planetArrayList.add(planet);
        }
//        Planet planet = new Planet("Earth", 150, 10, 12750);
//        planetArrayList.add(planet);
//        planet = new Planet("Jupiter", 778, 26, 143000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mars", 228, 4, 6800);
//        planetArrayList.add(planet);
//        planet = new Planet("Pluto", 5900, 1, 2320);
//        planetArrayList.add(planet);
//        planet = new Planet("Venus", 108, 9, 12750);
//        planetArrayList.add(planet);
//        planet = new Planet("Saturn", 1429, 11, 120000);
//        planetArrayList.add(planet);
//        planet = new Planet("Mercury", 58, 4, 4900);
//        planetArrayList.add(planet);
//        planet = new Planet("Neptune", 4500, 12, 50500);
//        planetArrayList.add(planet);
//        planet = new Planet("Uranus", 2870, 9, 52400);
//        planetArrayList.add(planet);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
