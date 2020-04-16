package com.droidbyme.recyclerviewselection.district;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class DistrictDatum {

    @SerializedName("com/droidbyme/recyclerviewselection/district")
    @Expose
    private String district;
    @SerializedName("confirmed")
    @Expose
    private Integer confirmed;
    @SerializedName("lastupdatedtime")
    @Expose
    private String lastupdatedtime;
    @SerializedName("delta")
    @Expose
    private Delta delta;

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public Delta getDelta() {
        return delta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    public static Comparator<DistrictDatum> DistrictConfirmedComparatorDescendingOrder = new Comparator<DistrictDatum>() {

        public int compare(DistrictDatum districtDatum1, DistrictDatum districtDatum2) {
            Integer confirmed1 = districtDatum1.getConfirmed();
            Integer confirmed2 = districtDatum2.getConfirmed();

            //descending order
            return confirmed2 - confirmed1;
        }
    };
}