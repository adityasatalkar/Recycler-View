package com.droidbyme.recyclerviewselection.activity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateDistrictWise {

    @SerializedName("districtWise")
    @Expose
    private List<DistrictWise> districtWise = null;

    public List<DistrictWise> getDistrictWise() {
        return districtWise;
    }

    public void setDistrictWise(List<DistrictWise> districtWise) {
        this.districtWise = districtWise;
    }

}