package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class newuser {
    @SerializedName("OfrEmployeeId")
    @Expose
    private String ofrEmployeeId;

    @SerializedName("OfrCountryId")
    @Expose
    private String ofrCountryId;

    @SerializedName("OeId")
    @Expose
    private Integer oeId;

    @SerializedName("OfrActive")
    @Expose
    private String ofrActive;

    public String getOfrEmployeeId() {
        return ofrEmployeeId;
    }

    public void setOfrEmployeeId(String ofrEmployeeId) {
        this.ofrEmployeeId = ofrEmployeeId;
    }

    public String getOfrCountryId() {
        return ofrCountryId;
    }

    public void setOfrCountryId(String ofrCountryId) {
        this.ofrCountryId = ofrCountryId;
    }

    public Integer getOeId() {
        return oeId;
    }

    public void setOeId(Integer oeId) {
        this.oeId = oeId;
    }

    public String getOfrActive() {
        return ofrActive;
    }

    public void setOfrActive(String ofrActive) {
        this.ofrActive = ofrActive;
    }

}