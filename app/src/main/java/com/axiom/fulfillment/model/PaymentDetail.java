package com.axiom.fulfillment.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentDetail {

    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;


    @SerializedName("PaymentDesc")
    @Expose
    private String PaymentDesc;
    @SerializedName("paymentAmount")
    @Expose
    private Double paymentAmount;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;
    @SerializedName("paymentMode")
    @Expose
    private Double paymentMode;
    @SerializedName("localAmount")
    @Expose
    private Double localAmount;

    @SerializedName("PaymentOnDelivery")
    @Expose
    private String paymentOnDelivery;

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getPaymentDesc() {
        return PaymentDesc;
    }

    public void setPaymentDesc(String paymentDesc) {
        PaymentDesc = paymentDesc;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(Double paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Double getLocalAmount() {
        return localAmount;
    }

    public void setLocalAmount(Double localAmount) {
        this.localAmount = localAmount;
    }

    public String getPaymentOnDelivery() {
        return paymentOnDelivery;
    }

    public void setPaymentOnDelivery(String paymentOnDelivery) {
        this.paymentOnDelivery = paymentOnDelivery;
    }
}