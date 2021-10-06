package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class deviceDetails {
    @SerializedName("DeviceId")
    @Expose
    private String DeviceId;

    @SerializedName("DeviceType")
    @Expose
    private String DeviceType;

    @SerializedName("ApplicationName")
    @Expose
    private String ApplicationName;

    public deviceDetails(){
        DeviceType="ANDROID";
        ApplicationName="FULFIL-BIKER-APP";
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }
}
