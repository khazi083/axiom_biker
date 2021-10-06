package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Dashboard {
    @SerializedName("OrderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("StatusCount")
    @Expose
    private Integer statusCount;
    @SerializedName("Level1")
    @Expose
    private List<Level1> level1 = null;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public List<Level1> getLevel1() {
        return level1;
    }

    public void setLevel1(List<Level1> level1) {
        this.level1 = level1;
    }

}
