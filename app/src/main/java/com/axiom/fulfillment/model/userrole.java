package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userrole {

    @SerializedName("OeId")
    @Expose
    private Integer oeId;
    @SerializedName("OeDesc")
    @Expose
    private String oeDesc;

    public Integer getOeId() {
        return oeId;
    }

    public void setOeId(Integer oeId) {
        this.oeId = oeId;
    }

    public String getOeDesc() {
        return oeDesc;
    }

    public void setOeDesc(String oeDesc) {
        this.oeDesc = oeDesc;
    }

}