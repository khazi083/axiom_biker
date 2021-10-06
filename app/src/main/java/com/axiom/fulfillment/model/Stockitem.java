package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stockitem {

    @SerializedName("ErpItemId")
    @Expose
    private Double ErpItemId;
    @SerializedName("AclmChannel")
    @Expose
    private String AclmChannel;

    @SerializedName("AvailableStock")
    @Expose
    private Double AvailableStock;

    @SerializedName("AlnmLocationCode")
    @Expose
    private String AlnmLocationCode;
    @SerializedName("ProductCode")
    @Expose
    private String ProductCode;
    @SerializedName("ProductDescription")
    @Expose
    private String ProductDescription;

    public Double getErpItemId() {
        return ErpItemId;
    }

    public void setErpItemId(Double erpItemId) {
        ErpItemId = erpItemId;
    }

    public String getAclmChannel() {
        return AclmChannel;
    }

    public void setAclmChannel(String aclmChannel) {
        AclmChannel = aclmChannel;
    }

    public Double getAvailableStock() {
        return AvailableStock;
    }

    public void setAvailableStock(Double availableStock) {
        AvailableStock = availableStock;
    }

    public String getAlnmLocationCode() {
        return AlnmLocationCode;
    }

    public void setAlnmLocationCode(String alnmLocationCode) {
        AlnmLocationCode = alnmLocationCode;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }
}
