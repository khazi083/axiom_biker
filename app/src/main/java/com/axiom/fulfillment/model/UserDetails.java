package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("AudsUserId")
    @Expose
    private int AudsUserId;

    @SerializedName("AomsOrganizationCode")
    @Expose
    private String AomsOrganizationCode;

    @SerializedName("AudsUserName")
    @Expose
    private String AudsUserName;

    @SerializedName("AudsUserCode")
    @Expose
    private String AudsUserCode;

    @SerializedName("AemsEmployeeCode")
    @Expose
    private String AemsEmployeeCode;

    public String getAemsEmployeeCode() {
        return AemsEmployeeCode;
    }

    public void setAemsEmployeeCode(String aemsEmployeeCode) {
        AemsEmployeeCode = aemsEmployeeCode;
    }

    public UserDetails(){}
    public UserDetails(int userid, String name,String AudsUserCode,String hrcode){
        AudsUserId=userid;
        AudsUserName=name;
        this.AudsUserCode=AudsUserCode;
        this.AemsEmployeeCode=hrcode;
    }

    public UserDetails(int userid, String name){
        AudsUserId=userid;
        AudsUserName=name;
    }
    public int getAudsUserId() {
        return AudsUserId;
    }

    public String getAomsOrganizationCode() {
        return AomsOrganizationCode;
    }

    public void setAomsOrganizationCode(String aomsOrganizationCode) {
        AomsOrganizationCode = aomsOrganizationCode;
    }

    public String getAudsUserCode() {
        return AudsUserCode;
    }

    public void setAudsUserCode(String audsUserCode) {
        AudsUserCode = audsUserCode;
    }

    public void setAudsUserId(int audsUserId) {
        AudsUserId = audsUserId;
    }

    public String getAudsUserName() {
        return AudsUserName;
    }

    public void setAudsUserName(String audsUserName) {
        AudsUserName = audsUserName;
    }
}
