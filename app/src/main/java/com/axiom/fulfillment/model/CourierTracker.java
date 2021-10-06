package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierTracker {

    @SerializedName("CourierSequence")
    @Expose
    private Integer courierSequence;
    @SerializedName("StationCode")
    @Expose
    private String stationCode;
    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("Activity")
    @Expose
    private String activity;

    public Integer getCourierSequence() {
        return courierSequence;
    }

    public void setCourierSequence(Integer courierSequence) {
        this.courierSequence = courierSequence;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
