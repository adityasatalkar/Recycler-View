package com.droidbyme.recyclerviewselection.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.droidbyme.recyclerviewselection.R;
import com.droidbyme.recyclerviewselection.model.Data;
import com.droidbyme.recyclerviewselection.model.Statewise;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static HashMap<String, List<String>> stateNamesHashMap = new HashMap<>();
    public static HashMap<String, Integer> districtNamesHashMap = new HashMap<>();

    public static final String HOSPITALIZED = "Hospitalized";
    public static final String RECOVERED = "Recovered";
    public static final String DECEASED = "Deceased";
    public static final String CONFIRMED = "Confirmed";

    private AppCompatButton btnSingle;
    private AppCompatButton btnMultiple;
    private AppCompatButton btnSwipe;
    private AppCompatButton btnNormal;
    private AppCompatButton btnCardView;
    private AppCompatButton btnMultipleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                initView();
                //tryTheApi();
                //Data data = getData();
                //printAll(data);
            }
            catch (Exception e) {}
            startActivity(new Intent(MainActivity.this, NormalRecyclerViewActivity.class));
        }

        btnCardView.setOnClickListener(this);
        btnSingle.setOnClickListener(this);
        btnMultiple.setOnClickListener(this);
        btnSwipe.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnMultipleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSingle:
                startActivity(new Intent(MainActivity.this, SingleSelectionActivity.class));
                break;

            case R.id.btnMultiple:
                startActivity(new Intent(MainActivity.this, MultipleSelectionActivity.class));
                break;

            case R.id.btnSwipe:
                startActivity(new Intent(MainActivity.this, SwipeSelectionActivity.class));
                break;

            case R.id.btnNormal:
                startActivity(new Intent(MainActivity.this, NormalRecyclerViewActivity.class));
                break;

            case R.id.btnCardView:
                startActivity(new Intent(MainActivity.this, CardRecyclerViewActivity.class));
                break;

            case R.id.btnMultipleView:
                startActivity(new Intent(MainActivity.this, MultipleViewTypeActivity.class));
                break;
        }
    }

    private void initView() {
        btnNormal = (AppCompatButton) findViewById(R.id.btnNormal);
        btnMultiple = (AppCompatButton) findViewById(R.id.btnMultiple);
        btnSingle = (AppCompatButton) findViewById(R.id.btnSingle);
        btnSwipe = (AppCompatButton) findViewById(R.id.btnSwipe);
        btnCardView = (AppCompatButton) findViewById(R.id.btnCardView);
        btnMultipleView = (AppCompatButton) findViewById(R.id.btnMultipleView);
    }

    private void tryTheApi() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            String jsonString = ApiCall.getDataFromApi(ApiCall.DATA_URL);

            Data data = gson.fromJson(jsonString, Data.class);

            List<Statewise> statewiseList = data.getStatewise();

            for (Statewise stateWiseObject: statewiseList) {
                System.out.println("*********");
                System.out.println("State " + stateWiseObject.getState());
                System.out.println(CONFIRMED + " " + stateWiseObject.getConfirmed());
                System.out.println(HOSPITALIZED + " " + stateWiseObject.getActive());
                System.out.println(RECOVERED + " " + stateWiseObject.getRecovered());
                System.out.println(DECEASED + " " + stateWiseObject.getDeaths());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Data getData() throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = ApiCall.getDataFromApi(ApiCall.DATA_URL);

        Data data = gson.fromJson(jsonString, Data.class);

        return data;
    }

    public static DistrictWise getDistrictWiseData(String state) throws Exception {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = "{\"districtWise\":" + ApiCall.getDataFromApi(ApiCall.STATE_DISTRICT_WISE_V2_URL) + "}";

        StateDistrictWise stateDistrictWise = gson.fromJson(jsonString, StateDistrictWise.class);
        List<DistrictWise> districtWiseList = stateDistrictWise.getDistrictWise();
        for (DistrictWise districtWiseObject: districtWiseList) {
            if (districtWiseObject.getState().equalsIgnoreCase(state)) {
                return districtWiseObject;
            }
        }
        return null;
    }

    public static void printAll(Data data) {
        List<Statewise> statewiseList = data.getStatewise();

        try {
            for (Statewise stateWiseObject : statewiseList) {

                System.out.println("*********");
                System.out.println("State " + stateWiseObject.getState());
                System.out.println(CONFIRMED + " " + stateWiseObject.getConfirmed());
                System.out.println(HOSPITALIZED + " " + stateWiseObject.getActive());
                System.out.println(RECOVERED + " " + stateWiseObject.getRecovered());
                System.out.println(DECEASED + " " + stateWiseObject.getDeaths());

                //printData(districtWiseObject);
                DistrictWise districtWiseObject = getDistrictWiseData(stateWiseObject.getState());
                if (!stateWiseObject.getState().equalsIgnoreCase("Total")) {
                    System.out.println();
                    try {
                        List<DistrictDatum> districtDataList = districtWiseObject.getDistrictData();
                        Collections.sort(districtDataList, DistrictDatum.DistrictConfirmedComparatorDescendingOrder);
                        for (DistrictDatum districtDatumObject : districtDataList) {
                            System.out.println(districtDatumObject.getDistrict() + " " + districtDatumObject.getConfirmed());
                        }
                    }
                    catch (NullPointerException ne) {}
                }
                System.out.println();
            }
        }
        catch (Exception e) {}
        System.out.println();
    }
}