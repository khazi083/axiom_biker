package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class org_Channels {
    @SerializedName("AclmChannelCode")
    @Expose
    private String aclmChannelCode;
    @SerializedName("AbudBusinessUnitCode")
    @Expose
    private String abudBusinessUnitCode;
    @SerializedName("AomsOrganizationCode")
    @Expose
    private String aomsOrganizationCode;

    @SerializedName("AclmChannelDesc")
    @Expose
    private String aclmChannelDesc;
    @SerializedName("AclmChannelShortName")
    @Expose
    private String aclmChannelShortName;

    public String getAclmChannelCode() {
        return aclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        this.aclmChannelCode = aclmChannelCode;
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

    public String getAclmChannelDesc() {
        return aclmChannelDesc;
    }

    public void setAclmChannelDesc(String aclmChannelDesc) {
        this.aclmChannelDesc = aclmChannelDesc;
    }

    public String getAclmChannelShortName() {
        return aclmChannelShortName;
    }

    public void setAclmChannelShortName(String aclmChannelShortName) {
        this.aclmChannelShortName = aclmChannelShortName;
    }
}
