package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PosXorders {

    @SerializedName("Orders")
    @Expose
    private List<DispatchOrderitem> orderData = null;

    public List<DispatchOrderitem> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<DispatchOrderitem> orderData) {
        this.orderData = orderData;
    }

}