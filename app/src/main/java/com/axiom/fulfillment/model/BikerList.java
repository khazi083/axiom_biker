package com.axiom.fulfillment.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BikerList {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Bikers")
    @Expose
    private List<Biker> bikers = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Biker> getBikers() {
        return bikers;
    }

    public void setBikers(List<Biker> bikers) {
        this.bikers = bikers;
    }

}