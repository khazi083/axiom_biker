package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class deliveryorder {

    @SerializedName("OaboId")
    @Expose
    private Integer oaboId;
    @SerializedName("OadbId")
    @Expose
    private Integer oadbId;
    @SerializedName("OaboStatus")
    @Expose
    private String oaboStatus;
    @SerializedName("OaboCreatedOn")
    @Expose
    private String oaboCreatedOn;
    @SerializedName("OaboCreatedBy")
    @Expose
    private String oaboCreatedBy;
    @SerializedName("ObohOrderNo")
    @Expose
    private String obohOrderNo;
    @SerializedName("ObohCustFullName")
    @Expose
    private String obohCustFullName;
    @SerializedName("ObohOrderDate")
    @Expose
    private String obohOrderDate;
    @SerializedName("ObohOrderSource")
    @Expose
    private String obohOrderSource;
    @SerializedName("ObohOrderStatus")
    @Expose
    private String obohOrderStatus;
    @SerializedName("ObohMoveOrderNo")
    @Expose
    private String obohMoveOrderNo;
    @SerializedName("ObohPartnerOrderNo")
    @Expose
    private String obohPartnerOrderNo;
    @SerializedName("OadbEmployeeId")
    @Expose
    private String oadbEmployeeId;
    @SerializedName("EmployeeName")
    @Expose
    private String employeeName;

    @SerializedName("OrderDeliveredGps")
    @Expose
    private String OrderDeliveredGps;

    @SerializedName("OrderPickedGps")
    @Expose
    private String OrderPickedGps;


    @SerializedName("OrdPickOrderLocation")
    @Expose
    private String OrdPickOrderLocation;

    public String getOrdPickOrderLocation() {
        return OrdPickOrderLocation;
    }

    public void setOrdPickOrderLocation(String ordPickOrderLocation) {
        OrdPickOrderLocation = ordPickOrderLocation;
    }

    public String getOrderDeliveredGps() {
        return OrderDeliveredGps;
    }

    public void setOrderDeliveredGps(String orderDeliveredGps) {
        OrderDeliveredGps = orderDeliveredGps;
    }

    public String getOrderPickedGps() {
        return OrderPickedGps;
    }

    public void setOrderPickedGps(String orderPickedGps) {
        OrderPickedGps = orderPickedGps;
    }

    public Integer getOaboId() {
        return oaboId;
    }

    public void setOaboId(Integer oaboId) {
        this.oaboId = oaboId;
    }

    public Integer getOadbId() {
        return oadbId;
    }

    public void setOadbId(Integer oadbId) {
        this.oadbId = oadbId;
    }

    public String getOaboStatus() {
        return oaboStatus;
    }

    public void setOaboStatus(String oaboStatus) {
        this.oaboStatus = oaboStatus;
    }

    public String getOaboCreatedOn() {
        return oaboCreatedOn;
    }

    public void setOaboCreatedOn(String oaboCreatedOn) {
        this.oaboCreatedOn = oaboCreatedOn;
    }

    public String getOaboCreatedBy() {
        return oaboCreatedBy;
    }

    public void setOaboCreatedBy(String oaboCreatedBy) {
        this.oaboCreatedBy = oaboCreatedBy;
    }

    public String getObohOrderNo() {
        return obohOrderNo;
    }

    public void setObohOrderNo(String obohOrderNo) {
        this.obohOrderNo = obohOrderNo;
    }

    public String getObohCustFullName() {
        return obohCustFullName;
    }

    public void setObohCustFullName(String obohCustFullName) {
        this.obohCustFullName = obohCustFullName;
    }

    public String getObohOrderDate() {
        return obohOrderDate;
    }

    public void setObohOrderDate(String obohOrderDate) {
        this.obohOrderDate = obohOrderDate;
    }

    public String getObohOrderSource() {
        return obohOrderSource;
    }

    public void setObohOrderSource(String obohOrderSource) {
        this.obohOrderSource = obohOrderSource;
    }

    public String getObohOrderStatus() {
        return obohOrderStatus;
    }

    public void setObohOrderStatus(String obohOrderStatus) {
        this.obohOrderStatus = obohOrderStatus;
    }

    public String getObohMoveOrderNo() {
        return obohMoveOrderNo;
    }

    public void setObohMoveOrderNo(String obohMoveOrderNo) {
        this.obohMoveOrderNo = obohMoveOrderNo;
    }

    public String getObohPartnerOrderNo() {
        return obohPartnerOrderNo;
    }

    public void setObohPartnerOrderNo(String obohPartnerOrderNo) {
        this.obohPartnerOrderNo = obohPartnerOrderNo;
    }

    public String getOadbEmployeeId() {
        return oadbEmployeeId;
    }

    public void setOadbEmployeeId(String oadbEmployeeId) {
        this.oadbEmployeeId = oadbEmployeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

}