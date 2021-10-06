package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class menurole {

    @SerializedName("RoleId")
    @Expose
    private Integer RoleId;

    @SerializedName("AppActive")
    @Expose
    private String AppActive;

    public Integer getRoleId() {
        return RoleId;
    }

    public void setRoleId(Integer roleId) {
        RoleId = roleId;
    }

    public String getAppActive() {
        return AppActive;
    }

    public void setAppActive(String appActive) {
        AppActive = appActive;
    }
}
