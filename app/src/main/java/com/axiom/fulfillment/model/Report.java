package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {
    @SerializedName("ObosdId")
    @Expose
    private Double obosdId;
    @SerializedName("ObohOrderNo")
    @Expose
    private String obohOrderNo;
    @SerializedName("OslId")
    @Expose
    private Double oslId;
    @SerializedName("ObosdData")
    @Expose
    private String obosdData;

    public Double getObosdId() {
        return obosdId;
    }

    public void setObosdId(Double obosdId) {
        this.obosdId = obosdId;
    }

    public String getObohOrderNo() {
        return obohOrderNo;
    }

    public void setObohOrderNo(String obohOrderNo) {
        this.obohOrderNo = obohOrderNo;
    }

    public Double getOslId() {
        return oslId;
    }

    public void setOslId(Double oslId) {
        this.oslId = oslId;
    }

    public String getObosdData() {
        return obosdData;
    }

    public void setObosdData(String obosdData) {
        this.obosdData = obosdData;
    }

}
