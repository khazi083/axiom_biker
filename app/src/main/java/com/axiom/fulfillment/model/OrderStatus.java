package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderStatus {

    @SerializedName("OaboId")
    @Expose
    private Integer oaboId;
    @SerializedName("OadbId")
    @Expose
    private Integer oadbId;
    @SerializedName("OaboStatus")
    @Expose
    private String oaboStatus;
    @SerializedName("OaboComments")
    @Expose
    private String oaboComments;

    public Integer getOaboId() {
        return oaboId;
    }

    public void setOaboId(Integer oaboId) {
        this.oaboId = oaboId;
    }

    public Integer getOadbId() {
        return oadbId;
    }

    public void setOadbId(Integer oadbId) {
        this.oadbId = oadbId;
    }

    public String getOaboStatus() {
        return oaboStatus;
    }

    public void setOaboStatus(String oaboStatus) {
        this.oaboStatus = oaboStatus;
    }

    public String getOaboComments() {
        return oaboComments;
    }

    public void setOaboComments(String oaboComments) {
        this.oaboComments = oaboComments;
    }
}
