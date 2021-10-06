package com.axiom.fulfillment.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationMaster {

    @SerializedName("AlhrHierarchyCode")
    @Expose
    private Integer alhrHierarchyCode;
    @SerializedName("AbudBusinessUnitCode")
    @Expose
    private String abudBusinessUnitCode;
    @SerializedName("AomsOrganizationCode")
    @Expose
    private String aomsOrganizationCode;
    @SerializedName("AclmChannelCode")
    @Expose
    private String aclmChannelCode;
    @SerializedName("AlnmLocationCode")
    @Expose
    private String alnmLocationCode;
    @SerializedName("AlnmLocationDesc")
    @Expose
    private String alnmLocationDesc;
    @SerializedName("AlnmLocationShortDesc")
    @Expose
    private String alnmLocationShortDesc;
    @SerializedName("AlnmLocationAddress1")
    @Expose
    private String alnmLocationAddress1;

    public Integer getAlhrHierarchyCode() {
        return alhrHierarchyCode;
    }

    public void setAlhrHierarchyCode(Integer alhrHierarchyCode) {
        this.alhrHierarchyCode = alhrHierarchyCode;
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

    public String getAclmChannelCode() {
        return aclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        this.aclmChannelCode = aclmChannelCode;
    }

    public String getAlnmLocationCode() {
        return alnmLocationCode;
    }

    public void setAlnmLocationCode(String alnmLocationCode) {
        this.alnmLocationCode = alnmLocationCode;
    }

    public String getAlnmLocationDesc() {
        return alnmLocationDesc;
    }

    public void setAlnmLocationDesc(String alnmLocationDesc) {
        this.alnmLocationDesc = alnmLocationDesc;
    }

    public String getAlnmLocationShortDesc() {
        return alnmLocationShortDesc;
    }

    public void setAlnmLocationShortDesc(String alnmLocationShortDesc) {
        this.alnmLocationShortDesc = alnmLocationShortDesc;
    }

    public String getAlnmLocationAddress1() {
        return alnmLocationAddress1;
    }

    public void setAlnmLocationAddress1(String alnmLocationAddress1) {
        this.alnmLocationAddress1 = alnmLocationAddress1;
    }
}