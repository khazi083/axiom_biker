package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class orderApi {
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    @SerializedName("OaboStatus")
    @Expose
    private String OaboStatus;

    @SerializedName("OgmlGeneralRemarks")
    @Expose
    private String OgmlGeneralRemarks;

    @SerializedName("OadbEmployeeId")
    @Expose
    private String OadbEmployeeId;

    public String getOadbEmployeeId() {
        return OadbEmployeeId;
    }

    public void setOadbEmployeeId(String oadbEmployeeId) {
        OadbEmployeeId = oadbEmployeeId;
    }

    public String getOgmlGeneralRemarks() {
        return OgmlGeneralRemarks;
    }

    public void setOgmlGeneralRemarks(String ogmlGeneralRemarks) {
        OgmlGeneralRemarks = ogmlGeneralRemarks;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getstatus() {
        return OaboStatus;
    }

    public void setstaus(String status) {
        this.OaboStatus = status;
    }

}

