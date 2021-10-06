package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemDetails {
    @SerializedName("LineSequence")
    @Expose
    private Double lineSequence;
    @SerializedName("OrderNo")
    @Expose
    private String orderNo;
    @SerializedName("ItemId")
    @Expose
    private Double itemId;
    @SerializedName("itemCode")
    @Expose
    private String itemCode;
    @SerializedName("itemDescription")
    @Expose
    private String itemDescription;
    @SerializedName("itemShortDesc")
    @Expose
    private String itemShortDesc;
    @SerializedName("ActualObodItemQty")
    @Expose
    private int actualObodItemQty;
    @SerializedName("quantity")
    @Expose
    private Double quantity;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("itemValue")
    @Expose
    private Double itemValue;
    @SerializedName("ItemStock")
    @Expose
    private Double itemStock;

    @SerializedName("ItemAmount")
    @Expose
    private Double itemAmount;
    @SerializedName("ItemTotAmount")
    @Expose
    private Double itemTotAmount;
    @SerializedName("DiscountPercentage")
    @Expose
    private Double discountPercentage;
    @SerializedName("DiscountValue")
    @Expose
    private Double discountValue;
    @SerializedName("TaxAmount")
    @Expose
    private Double taxAmount;

    @SerializedName("LineStatus")
    @Expose
    private String lineStatus;

    @SerializedName("ItemAmountTax")
    @Expose
    private Double itemAmountTax;
    @SerializedName("TaxPercent")
    @Expose
    private Double taxPercent;
    @SerializedName("ObodConsigmentNo")
    @Expose
    private String obodConsigmentNo;
    @SerializedName("ObodPartnerTaxDef")
    @Expose
    private Object obodPartnerTaxDef;
    @SerializedName("ObodQtyShipped")
    @Expose
    private Double obodQtyShipped;
    @SerializedName("ObodOrderStatus")
    @Expose
    private String obodOrderStatus;

    @SerializedName("ObodPickedStatus")
    @Expose
    private String obodPickedStatus;


    @SerializedName("ItmrItemStockable")
    @Expose
    private String ItmrItemStockable;

    public String getItmrItemStockable() {
        return ItmrItemStockable;
    }

    public void setItmrItemStockable(String itmrItemStockable) {
        ItmrItemStockable = itmrItemStockable;
    }

    public Double getLineSequence() {
        return lineSequence;
    }

    public void setLineSequence(Double lineSequence) {
        this.lineSequence = lineSequence;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getItemId() {
        return itemId;
    }

    public void setItemId(Double itemId) {
        this.itemId = itemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemShortDesc() {
        return itemShortDesc;
    }

    public void setItemShortDesc(String itemShortDesc) {
        this.itemShortDesc = itemShortDesc;
    }

    public int getActualObodItemQty() {
        return actualObodItemQty;
    }

    public void setActualObodItemQty(int actualObodItemQty) {
        this.actualObodItemQty = actualObodItemQty;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getItemValue() {
        return itemValue;
    }

    public void setItemValue(Double itemValue) {
        this.itemValue = itemValue;
    }

    public Double getItemStock() {
        return itemStock;
    }

    public void setItemStock(Double itemStock) {
        this.itemStock = itemStock;
    }

    public Double getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Double itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Double getItemTotAmount() {
        return itemTotAmount;
    }

    public void setItemTotAmount(Double itemTotAmount) {
        this.itemTotAmount = itemTotAmount;
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

    public String getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    public Double getItemAmountTax() {
        return itemAmountTax;
    }

    public void setItemAmountTax(Double itemAmountTax) {
        this.itemAmountTax = itemAmountTax;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public String getObodConsigmentNo() {
        return obodConsigmentNo;
    }

    public void setObodConsigmentNo(String obodConsigmentNo) {
        this.obodConsigmentNo = obodConsigmentNo;
    }

    public Object getObodPartnerTaxDef() {
        return obodPartnerTaxDef;
    }

    public void setObodPartnerTaxDef(Object obodPartnerTaxDef) {
        this.obodPartnerTaxDef = obodPartnerTaxDef;
    }

    public Double getObodQtyShipped() {
        return obodQtyShipped;
    }

    public void setObodQtyShipped(Double obodQtyShipped) {
        this.obodQtyShipped = obodQtyShipped;
    }

    public String getObodOrderStatus() {
        return obodOrderStatus;
    }

    public void setObodOrderStatus(String obodOrderStatus) {
        this.obodOrderStatus = obodOrderStatus;
    }

    public String getObodPickedStatus() {
        return obodPickedStatus;
    }

    public void setObodPickedStatus(String obodPickedStatus) {
        this.obodPickedStatus = obodPickedStatus;
    }
}
