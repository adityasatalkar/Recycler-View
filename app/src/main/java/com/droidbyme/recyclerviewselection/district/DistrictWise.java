package com.droidbyme.recyclerviewselection.district;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DistrictWise {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("districtData")
    @Expose
    private List<DistrictDatum> districtData = null;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<DistrictDatum> getDistrictData() {
        return districtData;
    }

    public void setDistrictData(List<DistrictDatum> districtData) {
        this.districtData = districtData;
    }
}