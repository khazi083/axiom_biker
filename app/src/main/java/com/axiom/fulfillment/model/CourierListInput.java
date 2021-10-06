package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierListInput {

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;
    @SerializedName("FulFilSystem")
    @Expose
    private String fulFilSystem;
    @SerializedName("ChannelCode")
    @Expose
    private String aclmChannelCode;

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getFulFilSystem() {
        return fulFilSystem;
    }

    public void setFulFilSystem(String fulFilSystem) {
        this.fulFilSystem = fulFilSystem;
    }

    public String getAclmChannelCode() {
        return aclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        this.aclmChannelCode = aclmChannelCode;
    }

}