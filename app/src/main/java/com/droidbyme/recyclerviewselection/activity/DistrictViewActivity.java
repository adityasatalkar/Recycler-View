package com.droidbyme.recyclerviewselection.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.adapter.DistrictDataAdapter;
import com.droidbyme.recyclerviewselection.district.DistrictDatum;
import com.droidbyme.recyclerviewselection.district.DistrictWise;

import java.util.ArrayList;
import java.util.List;

import static com.droidbyme.recyclerviewselection.activity.MainActivity.getDistrictWiseData;

public class DistrictViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DistrictDataAdapter adapter;
    private ArrayList<DistrictDatum> districtDatumArrayList;
    private String stateName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        Bundle bundle = getIntent().getExtras();
        stateName = bundle.getString("stateName");

        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("District Wise List");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.districtRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        districtDatumArrayList = new ArrayList<>();
        adapter = new DistrictDataAdapter(this, districtDatumArrayList);
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
        DistrictWise data = getDistrictWiseData(this.stateName);
        List<DistrictDatum> districtDatumList = data.getDistrictData();

        districtDatumArrayList = (ArrayList<DistrictDatum>) districtDatumList;

        adapter.notifyDataSetChanged();
    }
}
