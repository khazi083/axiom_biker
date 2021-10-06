package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShipmentReportinput {

    @SerializedName("UserDetails")
    @Expose
    private UserDetails user;

    @SerializedName("AxiomOrderNo")
    @Expose
    private String AxiomOrderNo;

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public String getAxiomOrderNo() {
        return AxiomOrderNo;
    }

    public void setAxiomOrderNo(String axiomOrderNo) {
        AxiomOrderNo = axiomOrderNo;
    }
}
