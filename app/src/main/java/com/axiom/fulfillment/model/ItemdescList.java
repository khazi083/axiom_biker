package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemdescList {
    @SerializedName("ItemDetail")
    @Expose
    private List<ItemDetail> itemDetail = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<ItemDetail> getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(List<ItemDetail> itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
