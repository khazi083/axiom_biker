package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Biker {

    @SerializedName("OadbEmployeeId")
    @Expose
    private String oadbEmployeeId;
    @SerializedName("OadbId")
    @Expose
    private Double oadbId;
    @SerializedName("OadbActive")
    @Expose
    private String oadbActive;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;
    @SerializedName("EmployeeEmail")
    @Expose
    private String employeeEmail;

    public String getOadbEmployeeId() {
        return oadbEmployeeId;
    }

    public void setOadbEmployeeId(String oadbEmployeeId) {
        this.oadbEmployeeId = oadbEmployeeId;
    }

    public Double getOadbId() {
        return oadbId;
    }

    public void setOadbId(Double oadbId) {
        this.oadbId = oadbId;
    }

    public String getOadbActive() {
        return oadbActive;
    }

    public void setOadbActive(String oadbActive) {
        this.oadbActive = oadbActive;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

}
