package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailedMenu {

    @SerializedName("DtlMenuDesc")
    @Expose
    private String dtlMenuDesc;
    @SerializedName("DtlMenuLink")
    @Expose
    private String dtlMenuLink;
    @SerializedName("DtlMenuActive")
    @Expose
    private String dtlMenuActive;
    @SerializedName("DtlMenuFunction")
    @Expose
    private String dtlMenuFunction;
    @SerializedName("DtlMenuTitle")
    @Expose
    private String dtlMenuTitle;
    @SerializedName("OmimTitle")
    @Expose
    private String omimTitle;
    @SerializedName("HeadMenuId")
    @Expose
    private Integer headMenuId;
    @SerializedName("DetlMenuId")
    @Expose
    private Integer detlMenuId;
    @SerializedName("OmchOrder")
    @Expose
    private Integer omchOrder;

    public String getDtlMenuDesc() {
        return dtlMenuDesc;
    }

    public void setDtlMenuDesc(String dtlMenuDesc) {
        this.dtlMenuDesc = dtlMenuDesc;
    }

    public String getDtlMenuLink() {
        return dtlMenuLink;
    }

    public void setDtlMenuLink(String dtlMenuLink) {
        this.dtlMenuLink = dtlMenuLink;
    }

    public String getDtlMenuActive() {
        return dtlMenuActive;
    }

    public void setDtlMenuActive(String dtlMenuActive) {
        this.dtlMenuActive = dtlMenuActive;
    }

    public String getDtlMenuFunction() {
        return dtlMenuFunction;
    }

    public void setDtlMenuFunction(String dtlMenuFunction) {
        this.dtlMenuFunction = dtlMenuFunction;
    }

    public String getDtlMenuTitle() {
        return dtlMenuTitle;
    }

    public void setDtlMenuTitle(String dtlMenuTitle) {
        this.dtlMenuTitle = dtlMenuTitle;
    }

    public String getOmimTitle() {
        return omimTitle;
    }

    public void setOmimTitle(String omimTitle) {
        this.omimTitle = omimTitle;
    }

    public Integer getHeadMenuId() {
        return headMenuId;
    }

    public void setHeadMenuId(Integer headMenuId) {
        this.headMenuId = headMenuId;
    }

    public Integer getDetlMenuId() {
        return detlMenuId;
    }

    public void setDetlMenuId(Integer detlMenuId) {
        this.detlMenuId = detlMenuId;
    }

    public Integer getOmchOrder() {
        return omchOrder;
    }

    public void setOmchOrder(Integer omchOrder) {
        this.omchOrder = omchOrder;
    }

}