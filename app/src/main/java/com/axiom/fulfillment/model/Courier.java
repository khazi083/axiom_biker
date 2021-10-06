package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Courier {

    @SerializedName("CourierID")
    @Expose
    private Double courierID;
    @SerializedName("CourierName")
    @Expose
    private String courierName;

    public Double getCourierID() {
        return courierID;
    }

    public void setCourierID(Double courierID) {
        this.courierID = courierID;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

}