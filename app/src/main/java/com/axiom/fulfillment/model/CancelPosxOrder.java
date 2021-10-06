package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelPosxOrder {

    @SerializedName("AxiomOrderNo")
    @Expose
    private String AxiomOrderNo;

    @SerializedName("OrderSequence")
    @Expose
    private Double OrderSequence;

    @SerializedName("CancelReason")
    @Expose
    private String CancelReason;

    @SerializedName("OrderSource")
    @Expose
    private String OrderSource;

    @SerializedName("EPurchaseNo")
    @Expose
    private Double EPurchaseNo;


    @SerializedName("StockChannel")
    @Expose
    private String StockChannel;

    @SerializedName("StockLocation")
    @Expose
    private Double StockLocation;

    @SerializedName("OrderStatus")
    @Expose
    private String OrderStatus;

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    public String getAxiomOrderNo() {
        return AxiomOrderNo;
    }

    public void setAxiomOrderNo(String axiomOrderNo) {
        AxiomOrderNo = axiomOrderNo;
    }

    public Double getOrderSequence() {
        return OrderSequence;
    }

    public void setOrderSequence(Double orderSequence) {
        OrderSequence = orderSequence;
    }

    public String getCancelReason() {
        return CancelReason;
    }

    public void setCancelReason(String cancelReason) {
        CancelReason = cancelReason;
    }

    public String getOrderSource() {
        return OrderSource;
    }

    public void setOrderSource(String orderSource) {
        OrderSource = orderSource;
    }

    public Double getEPurchaseNo() {
        return EPurchaseNo;
    }

    public void setEPurchaseNo(Double EPurchaseNo) {
        this.EPurchaseNo = EPurchaseNo;
    }

    public String getStockChannel() {
        return StockChannel;
    }

    public void setStockChannel(String stockChannel) {
        StockChannel = stockChannel;
    }

    public Double getStockLocation() {
        return StockLocation;
    }

    public void setStockLocation(Double stockLocation) {
        StockLocation = stockLocation;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
