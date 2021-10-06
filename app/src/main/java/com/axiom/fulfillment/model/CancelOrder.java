package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelOrder {


    @SerializedName("ObohOrderNo")
    @Expose
    private String ObohOrderNo;

    @SerializedName("ObohSeq")
    @Expose
    private Double obohSeq;

    @SerializedName("Reason")
    @Expose
    private String Reason;

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    public String getObohOrderNo() {
        return ObohOrderNo;
    }

    public void setObohOrderNo(String obohOrderNo) {
        ObohOrderNo = obohOrderNo;
    }

    public Double getObohSeq() {
        return obohSeq;
    }

    public void setObohSeq(Double obohSeq) {
        this.obohSeq = obohSeq;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
