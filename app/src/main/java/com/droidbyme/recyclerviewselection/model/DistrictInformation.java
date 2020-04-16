package com.droidbyme.recyclerviewselection.model;

public class DistrictInformation {

    private String districtName;
    private int confirmed;

    public DistrictInformation(String districtName, int confirmed) {
        this.districtName = districtName;
        this.confirmed = confirmed;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

}
