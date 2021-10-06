package com.axiom.fulfillment.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ButtonStatus {

    @SerializedName("UserRole")
    @Expose
    private String userRole;
    @SerializedName("ObohSeq")
    @Expose
    private Double obohSeq;

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Double getObohSeq() {
        return obohSeq;
    }

    public void setObohSeq(Double obohSeq) {
        this.obohSeq = obohSeq;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}