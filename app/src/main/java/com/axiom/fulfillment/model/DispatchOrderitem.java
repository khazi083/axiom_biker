package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DispatchOrderitem {

    @SerializedName("ObohSeq")
    @Expose
    private String ObohSeq;
    @SerializedName("AclmChannelCode")
    @Expose
    private String AclmChannelCode;
    @SerializedName("ObohOrderNo")
    @Expose
    private String obohOrderNo;
    @SerializedName("ObohCustFullName")
    @Expose
    private String obohCustFullName;
    @SerializedName("ObohOrderSource")
    @Expose
    private String obohOrderSource;
    @SerializedName("ObohOrderDate")
    @Expose
    private String obohOrderDate;
    @SerializedName("ObohDeliveredDate")
    @Expose
    private String obohDeliveredDate;
    @SerializedName("ObohOrderAmountTax")
    @Expose
    private Double obohOrderAmountTax;

    @SerializedName("ObohOrderTotAmount")
    @Expose
    private Double ObohOrderTotAmount;

    @SerializedName("ObohCurrency")
    @Expose
    private String ObohCurrency;

    @SerializedName("ObohPartnerOrderNo")
    @Expose
    private String ObohPartnerOrderNo;


    @SerializedName("StockLocationCode")
    @Expose
    private String StockLocationCode;

    @SerializedName("StockChannelCode")
    @Expose
    private String StockChannelCode;
    @SerializedName("ObohOrderStatus")
    @Expose
    private String ObohOrderStatus;


    @SerializedName("OrdPickOrderLocation")
    @Expose
    private String OrdPickOrderLocation;

    public String getOrdPickOrderLocation() {
        return OrdPickOrderLocation;
    }

    public void setOrdPickOrderLocation(String ordPickOrderLocation) {
        OrdPickOrderLocation = ordPickOrderLocation;
    }

    public String getStockLocationCode() {
        return StockLocationCode;
    }

    public void setStockLocationCode(String stockLocationCode) {
        StockLocationCode = stockLocationCode;
    }

    public String getStockChannelCode() {
        return StockChannelCode;
    }

    public void setStockChannelCode(String stockChannelCode) {
        StockChannelCode = stockChannelCode;
    }

    public String getObohOrderStatus() {
        return ObohOrderStatus;
    }

    public void setObohOrderStatus(String obohOrderStatus) {
        ObohOrderStatus = obohOrderStatus;
    }

    public Double getObohOrderTotAmount() {
        return ObohOrderTotAmount;
    }

    public void setObohOrderTotAmount(Double obohOrderTotAmount) {
        ObohOrderTotAmount = obohOrderTotAmount;
    }

    public String getAclmChannelCode() {
        return AclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        AclmChannelCode = aclmChannelCode;
    }

    public String getObohPartnerOrderNo() {
        return ObohPartnerOrderNo;
    }

    public void setObohPartnerOrderNo(String obohPartnerOrderNo) {
        ObohPartnerOrderNo = obohPartnerOrderNo;
    }

    public String getObohSeq() {
        return ObohSeq;
    }

    public void setObohSeq(String obohSeq) {
        ObohSeq = obohSeq;
    }

    public String getObohCurrency() {
        return ObohCurrency;
    }

    public void setObohCurrency(String obohCurrency) {
        ObohCurrency = obohCurrency;
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

    public String getObohOrderSource() {
        return obohOrderSource;
    }

    public void setObohOrderSource(String obohOrderSource) {
        this.obohOrderSource = obohOrderSource;
    }

    public String getObohOrderDate() {
        return obohOrderDate;
    }

    public void setObohOrderDate(String obohOrderDate) {
        this.obohOrderDate = obohOrderDate;
    }

    public String getObohDeliveredDate() {
        return obohDeliveredDate;
    }

    public void setObohDeliveredDate(String obohDeliveredDate) {
        this.obohDeliveredDate = obohDeliveredDate;
    }

    public Double getObohOrderAmountTax() {
        return obohOrderAmountTax;
    }

    public void setObohOrderAmountTax(Double obohOrderAmountTax) {
        this.obohOrderAmountTax = obohOrderAmountTax;
    }
}
