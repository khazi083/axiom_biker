package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("OutResult")
    @Expose
    private Boolean outResult;
    @SerializedName("OutMessage")
    @Expose
    private String outMessage;

    public Boolean getOutResult() {
        return outResult;
    }

    public void setOutResult(Boolean outResult) {
        this.outResult = outResult;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

}
