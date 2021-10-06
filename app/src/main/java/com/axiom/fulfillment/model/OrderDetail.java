package com.axiom.fulfillment.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {

    @SerializedName("OrderSequence")
    @Expose
    private Integer orderSequence;
    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("CcrsUserCode")
    @Expose
    private String ccrsUserCode;
    @SerializedName("orderNumber")
    @Expose
    private String orderNumber;
    @SerializedName("customerFullName")
    @Expose
    private String customerFullName;
    @SerializedName("orderSource")
    @Expose
    private String orderSource;
    @SerializedName("orderDate")
    @Expose
    private String orderDate;
    @SerializedName("RefOrderNo")
    @Expose
    private Object refOrderNo;

    @SerializedName("orderAmount")
    @Expose
    private Double orderAmount;
    @SerializedName("OrderAmountTax")
    @Expose
    private Double totalAmount;
    @SerializedName("discountPercentage")
    @Expose
    private Double discountPercentage;
    @SerializedName("discountValue")
    @Expose
    private Double discountValue;
    @SerializedName("taxAmount")
    @Expose
    private Double taxAmount;
    @SerializedName("serviceCharges")
    @Expose
    private Object serviceCharges;
    @SerializedName("ShippingAddress1")
    @Expose
    private String shippingAddress1;
    @SerializedName("customerMobile")
    @Expose
    private String customerMobile;
    @SerializedName("customerEmail")
    @Expose
    private String customerEmail;
    @SerializedName("customerContactNo")
    @Expose
    private String customerContactNo;
    @SerializedName("ShippingCountry")
    @Expose
    private String shippingCountry;
    @SerializedName("ShippingRegion")
    @Expose
    private String shippingRegion;
    @SerializedName("ShippingCity")
    @Expose
    private String shippingCity;
    @SerializedName("BillingAddress1")
    @Expose
    private String billingAddress1;
    @SerializedName("expectedDeliveryDate")
    @Expose
    private String expectedDeliveryDate;
    @SerializedName("expectedDeliveryTime")
    @Expose
    private String expectedDeliveryTime;
    @SerializedName("ImmediateDelivery")
    @Expose
    private String immediateDelivery;
    @SerializedName("Priority")
    @Expose
    private String priority;
    @SerializedName("cashOnDelivery")
    @Expose
    private String cashOnDelivery;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    @SerializedName("OrderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("additionalInfo")
    @Expose
    private String comments;
    @SerializedName("StatusReason")
    @Expose
    private String statusReason;
    @SerializedName("ShippingMethod")
    @Expose
    private String shippingMethod;
    @SerializedName("ShippingAgent")
    @Expose
    private String shippingAgent;
    @SerializedName("ApprxDeliveryDate")
    @Expose
    private String apprxDeliveryDate;
    @SerializedName("ObohPartnerOrderNo")
    @Expose
    private String ObohPartnerOrderNo;


    @SerializedName("BillingCountry")
    @Expose
    private String billingCountry;
    @SerializedName("BillingRegion")
    @Expose
    private String billingRegion;
    @SerializedName("BillingCity")
    @Expose
    private String billingCity;
    @SerializedName("EOrderNo")
    @Expose
    private String eOrderNo;
    @SerializedName("Platform")
    @Expose
    private String platform;

    @Expose
    private List<OrderItemDetails> orderDetails = null;
    @SerializedName("paymentDetails")
    @Expose
    private List<PaymentDetail> paymentDetails = null;

    public String getComments() {
        if(comments!=null)
            return comments.replace("\n","  ");
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(Integer orderSequence) {
        this.orderSequence = orderSequence;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getCcrsUserCode() {
        return ccrsUserCode;
    }

    public void setCcrsUserCode(String ccrsUserCode) {
        this.ccrsUserCode = ccrsUserCode;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Object getRefOrderNo() {
        return refOrderNo;
    }

    public void setRefOrderNo(Object refOrderNo) {
        this.refOrderNo = refOrderNo;
    }


    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Object getServiceCharges() {
        return serviceCharges;
    }

    public void setServiceCharges(Object serviceCharges) {
        this.serviceCharges = serviceCharges;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerContactNo() {
        return customerContactNo;
    }

    public void setCustomerContactNo(String customerContactNo) {
        this.customerContactNo = customerContactNo;
    }



    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public String getShippingRegion() {
        return shippingRegion;
    }

    public void setShippingRegion(String shippingRegion) {
        this.shippingRegion = shippingRegion;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getBillingAddress1() {
        return billingAddress1;
    }

    public void setBillingAddress1(String billingAddress1) {
        this.billingAddress1 = billingAddress1;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(String expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public void setExpectedDeliveryTime(String expectedDeliveryTime) {
        this.expectedDeliveryTime = expectedDeliveryTime;
    }

    public String getImmediateDelivery() {
        return immediateDelivery;
    }

    public void setImmediateDelivery(String immediateDelivery) {
        this.immediateDelivery = immediateDelivery;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(String cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }



    public String getRemarks() {
        if(remarks!=null)
            return remarks.replace("\n","  ");
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingAgent() {
        return shippingAgent;
    }

    public void setShippingAgent(String shippingAgent) {
        this.shippingAgent = shippingAgent;
    }

    public String getApprxDeliveryDate() {
        return apprxDeliveryDate;
    }

    public void setApprxDeliveryDate(String apprxDeliveryDate) {
        this.apprxDeliveryDate = apprxDeliveryDate;
    }

    public String getObohPartnerOrderNo() {
        return ObohPartnerOrderNo;
    }

    public void setObohPartnerOrderNo(String obohPartnerOrderNo) {
        ObohPartnerOrderNo = obohPartnerOrderNo;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getBillingRegion() {
        return billingRegion;
    }

    public void setBillingRegion(String billingRegion) {
        this.billingRegion = billingRegion;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String geteOrderNo() {
        return eOrderNo;
    }

    public void seteOrderNo(String eOrderNo) {
        this.eOrderNo = eOrderNo;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public List<OrderItemDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderItemDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public List<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(List<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
}