package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SystemCourier {

    @SerializedName("OcmId")
    @Expose
    private Double ocmId;
    @SerializedName("OcmName")
    @Expose
    private String ocmName;

    public Double getOcmId() {
        return ocmId;
    }

    public void setOcmId(Double ocmId) {
        this.ocmId = ocmId;
    }

    public String getOcmName() {
        return ocmName;
    }

    public void setOcmName(String ocmName) {
        this.ocmName = ocmName;
    }
}
