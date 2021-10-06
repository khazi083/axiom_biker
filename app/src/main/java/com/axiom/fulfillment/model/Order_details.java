package com.axiom.fulfillment.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_details {

    @SerializedName("OrderDetail")
    @Expose
    private List<OrderDetail> orderDetail = null;

    @SerializedName("Status")
    @Expose
    private Status status;

    public List<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}