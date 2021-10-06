package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class bikerdispatch {
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("OadbId")
    @Expose
    private Double oadbId;
    @SerializedName("ObohSeq")
    @Expose
    private Double obohSeq;
    @SerializedName("OaboStatus")
    @Expose
    private String oaboStatus;

    @SerializedName("ChannelCode")
    @Expose
    private String ChannelCode;

    public String getChannelCode() {
        return ChannelCode;
    }

    public void setChannelCode(String channelCode) {
        ChannelCode = channelCode;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Double getOadbId() {
        return oadbId;
    }

    public void setOadbId(Double oadbId) {
        this.oadbId = oadbId;
    }

    public Double getObohSeq() {
        return obohSeq;
    }

    public void setObohSeq(Double obohSeq) {
        this.obohSeq = obohSeq;
    }

    public String getOaboStatus() {
        return oaboStatus;
    }

    public void setOaboStatus(String oaboStatus) {
        this.oaboStatus = oaboStatus;
    }

}
