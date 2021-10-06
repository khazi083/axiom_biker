package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

        import java.util.List;

public class bikerorder {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("BikerOrders")
    @Expose
    private List<deliveryorder> bikerOrders = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<deliveryorder> getBikerOrders() {
        return bikerOrders;
    }

    public void setBikerOrders(List<deliveryorder> bikerOrders) {
        this.bikerOrders = bikerOrders;
    }

}
