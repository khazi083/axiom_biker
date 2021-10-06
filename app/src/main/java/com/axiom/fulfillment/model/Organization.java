package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {

    @SerializedName("AomsOrganizationCode")
    @Expose
    private String aomsOrganizationCode;
    @SerializedName("AbudBusinessUnitCode")
    @Expose
    private String abudBusinessUnitCode;
    @SerializedName("AomsFunctionalCurrCode")
    @Expose
    private String aomsFunctionalCurrCode;
    @SerializedName("AomsDisplayCurrCode")
    @Expose
    private String aomsDisplayCurrCode;
    @SerializedName("AomsReportingCurrCode")
    @Expose
    private String aomsReportingCurrCode;
    @SerializedName("AcgpGroupCode")
    @Expose
    private String acgpGroupCode;
    @SerializedName("AomsOrganizationName")
    @Expose
    private String aomsOrganizationName;

    public String getAomsOrganizationCode() {
        return aomsOrganizationCode;
    }

    public void setAomsOrganizationCode(String aomsOrganizationCode) {
        this.aomsOrganizationCode = aomsOrganizationCode;
    }

    public String getAbudBusinessUnitCode() {
        return abudBusinessUnitCode;
    }

    public void setAbudBusinessUnitCode(String abudBusinessUnitCode) {
        this.abudBusinessUnitCode = abudBusinessUnitCode;
    }

    public String getAomsFunctionalCurrCode() {
        return aomsFunctionalCurrCode;
    }

    public void setAomsFunctionalCurrCode(String aomsFunctionalCurrCode) {
        this.aomsFunctionalCurrCode = aomsFunctionalCurrCode;
    }

    public String getAomsDisplayCurrCode() {
        return aomsDisplayCurrCode;
    }

    public void setAomsDisplayCurrCode(String aomsDisplayCurrCode) {
        this.aomsDisplayCurrCode = aomsDisplayCurrCode;
    }

    public String getAomsReportingCurrCode() {
        return aomsReportingCurrCode;
    }

    public void setAomsReportingCurrCode(String aomsReportingCurrCode) {
        this.aomsReportingCurrCode = aomsReportingCurrCode;
    }

    public String getAcgpGroupCode() {
        return acgpGroupCode;
    }

    public void setAcgpGroupCode(String acgpGroupCode) {
        this.acgpGroupCode = acgpGroupCode;
    }

    public String getAomsOrganizationName() {
        return aomsOrganizationName;
    }

    public void setAomsOrganizationName(String aomsOrganizationName) {
        this.aomsOrganizationName = aomsOrganizationName;
    }

}