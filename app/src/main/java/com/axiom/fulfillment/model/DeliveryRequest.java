package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeliveryRequest {
    @SerializedName("BikerRequest")
    @Expose
    private BikerRequest bikerRequest;
    @SerializedName("UpdateDocs")
    @Expose
    private List<Attachements> updateDocs = null;
    @SerializedName("Action")
    @Expose
    private String action;

    public BikerRequest getBikerRequest() {
        return bikerRequest;
    }

    public void setBikerRequest(BikerRequest bikerRequest) {
        this.bikerRequest = bikerRequest;
    }

    public List<Attachements> getUpdateDocs() {
        return updateDocs;
    }

    public void setUpdateDocs(List<Attachements> updateDocs) {
        this.updateDocs = updateDocs;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
