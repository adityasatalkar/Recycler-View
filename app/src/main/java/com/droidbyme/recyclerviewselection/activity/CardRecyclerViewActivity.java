package com.droidbyme.recyclerviewselection.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.adapter.CardAdapter;
import com.droidbyme.recyclerviewselection.model.StateInformation;
import com.droidbyme.recyclerviewselection.state.StateWiseData;
import com.droidbyme.recyclerviewselection.state.Statewise;

import java.util.ArrayList;
import java.util.List;

import static com.droidbyme.recyclerviewselection.activity.MainActivity.getData;

public class CardRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private ArrayList<StateInformation> stateInformationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        initView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("State Wise Card View");
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stateInformationArrayList = new ArrayList<>();
        adapter = new CardAdapter(this, stateInformationArrayList);
        recyclerView.setAdapter(adapter);
       // recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        try {
            createListData();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createListData() throws Exception {
        StateWiseData data = getData();
        List<Statewise> statewiseList = data.getStatewise();
        for (Statewise stateWiseObject: statewiseList) {
            StateInformation stateInformation = new StateInformation(stateWiseObject.getState(),Integer.parseInt(stateWiseObject.getConfirmed()),Integer.parseInt(stateWiseObject.getActive()),Integer.parseInt(stateWiseObject.getRecovered()),Integer.parseInt(stateWiseObject.getDeaths()));
            //DistrictWise districtWiseObject = getDistrictWiseData(stateWiseObject.getState());
            stateInformationArrayList.add(stateInformation);
        }
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
