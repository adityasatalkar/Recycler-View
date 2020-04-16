package com.droidbyme.recyclerviewselection.model;

public class DistrictInformation {

    private String stateName;
    private int confirmed;

    public DistrictInformation(String stateName, int confirmed) {
        this.stateName = stateName;
        this.confirmed = confirmed;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

}
