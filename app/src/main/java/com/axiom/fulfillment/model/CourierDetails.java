package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourierDetails {
    @SerializedName("CourierTracker")
    @Expose
    private List<CourierTracker> courierTracker = null;
    @SerializedName("Vehicle")
    @Expose
    private String vehicle;
    @SerializedName("Driver")
    @Expose
    private String driver;

    @SerializedName("IsDelivered")
    @Expose
    private Boolean isDelivered;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<CourierTracker> getCourierTracker() {
        return courierTracker;
    }

    public void setCourierTracker(List<CourierTracker> courierTracker) {
        this.courierTracker = courierTracker;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Boolean getDelivered() {
        return isDelivered;
    }

    public void setDelivered(Boolean delivered) {
        isDelivered = delivered;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
