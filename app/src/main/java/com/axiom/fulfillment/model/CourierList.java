package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourierList {

    @SerializedName("SystemCouriers")
    @Expose
    private List<SystemCourier> systemCouriers = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<SystemCourier> getSystemCouriers() {
        return systemCouriers;
    }

    public void setSystemCouriers(List<SystemCourier> systemCouriers) {
        this.systemCouriers = systemCouriers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}