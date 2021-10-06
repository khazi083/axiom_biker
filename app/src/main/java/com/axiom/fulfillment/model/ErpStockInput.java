package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErpStockInput {

    @SerializedName("AlnmLocationCode")
    @Expose
    private String AlnmLocationCode;
    @SerializedName("ProductCode")
    @Expose
    private String ProductCode;

    @SerializedName("AclmChannel")
    @Expose
    private String AclmChannel;
    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

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

    public String getAclmChannel() {
        return AclmChannel;
    }

    public void setAclmChannel(String aclmChannel) {
        AclmChannel = aclmChannel;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
