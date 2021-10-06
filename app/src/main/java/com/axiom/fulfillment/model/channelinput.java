package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class channelinput {

    @SerializedName("AbudBusinessUnitCode")
    @Expose
    private String abudBusinessUnitCode;
    @SerializedName("AomsOrganizationCode")
    @Expose
    private String aomsOrganizationCode;

    @SerializedName("AclmChannelCode")
    @Expose
    private String AclmChannelCode;
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    public String getAclmChannelCode() {
        return AclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        AclmChannelCode = aclmChannelCode;
    }

    public String getAbudBusinessUnitCode() {
        return abudBusinessUnitCode;
    }

    public void setAbudBusinessUnitCode(String abudBusinessUnitCode) {
        this.abudBusinessUnitCode = abudBusinessUnitCode;
    }

    public String getAomsOrganizationCode() {
        return aomsOrganizationCode;
    }

    public void setAomsOrganizationCode(String aomsOrganizationCode) {
        this.aomsOrganizationCode = aomsOrganizationCode;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}