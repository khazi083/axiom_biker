package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierOrder {
    @SerializedName("AxiomOrderNo")
    @Expose
    private String axiomOrderNo;

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getAxiomOrderNo() {
        return axiomOrderNo;
    }

    public void setAxiomOrderNo(String axiomOrderNo) {
        this.axiomOrderNo = axiomOrderNo;
    }

}
