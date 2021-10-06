package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderStatusDetails {
    @SerializedName("orderDetails")
    @Expose
    private List<OrderStatus> orderDetails = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<OrderStatus> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderStatus> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}