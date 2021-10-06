package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsInput {
    @SerializedName("UserDetails")
    @Expose
    private UserDetails user;

    @SerializedName("OrderNo")
    @Expose
    private String OrderNo;

    @SerializedName("MoveOrderNo")
    @Expose
    private String MoveOrderNo;

    public String getMove_orderno() {
        return MoveOrderNo;
    }

    public void setMove_orderno(String MoveOrderNo) {
        this.MoveOrderNo = MoveOrderNo;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }
}
