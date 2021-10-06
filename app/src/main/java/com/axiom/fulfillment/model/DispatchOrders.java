package com.axiom.fulfillment.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispatchOrders {

    @SerializedName("OrderData")
    @Expose
    private List<DispatchOrderitem> orderData = null;

    public List<DispatchOrderitem> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<DispatchOrderitem> orderData) {
        this.orderData = orderData;
    }

}