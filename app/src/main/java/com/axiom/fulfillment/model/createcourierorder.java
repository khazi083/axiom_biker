package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class createcourierorder {
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("Orders")
    @Expose
    private List<CourierOrder> orders = null;
    @SerializedName("Courier")
    @Expose
    private Courier courier;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public List<CourierOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<CourierOrder> orders) {
        this.orders = orders;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

}
