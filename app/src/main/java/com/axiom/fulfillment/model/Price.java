package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {

    @SerializedName("AomsOrganizationCode")
    @Expose
    private String aomsOrganizationCode;
    @SerializedName("AclmChannelCode")
    @Expose
    private String aclmChannelCode;
    @SerializedName("AlhrHierarchyCode")
    @Expose
    private Double alhrHierarchyCode;
    @SerializedName("AlnmLocationCode")
    @Expose
    private String alnmLocationCode;
    @SerializedName("ItmrItemDescription")
    @Expose
    private String itmrItemDescription;
    @SerializedName("ItmrItemCode")
    @Expose
    private String itmrItemCode;
    @SerializedName("ProdCode")
    @Expose
    private String prodCode;
    @SerializedName("AlnmLocationDesc")
    @Expose
    private String alnmLocationDesc;
    @SerializedName("AcrmCurrCode")
    @Expose
    private String acrmCurrCode;
    @SerializedName("RspeSellpPrice")
    @Expose
    private Double rspeSellpPrice;
    @SerializedName("RspeSellpActiveFrom")
    @Expose
    private String rspeSellpActiveFrom;
    @SerializedName("RspeSellpNReviewDate")
    @Expose
    private String rspeSellpNReviewDate;
    @SerializedName("RspeCreationDate")
    @Expose
    private String rspeCreationDate;
    @SerializedName("RspeLastUpdateDate")
    @Expose
    private String rspeLastUpdateDate;
    @SerializedName("RspeTaxInclude")
    @Expose
    private String rspeTaxInclude;
    @SerializedName("RspeSellpTaxPercentage")
    @Expose
    private Double rspeSellpTaxPercentage;
    @SerializedName("RspeSellpWtTax")
    @Expose
    private Double rspeSellpWtTax;

    public String getAomsOrganizationCode() {
        return aomsOrganizationCode;
    }

    public void setAomsOrganizationCode(String aomsOrganizationCode) {
        this.aomsOrganizationCode = aomsOrganizationCode;
    }

    public String getAclmChannelCode() {
        return aclmChannelCode;
    }

    public void setAclmChannelCode(String aclmChannelCode) {
        this.aclmChannelCode = aclmChannelCode;
    }

    public Double getAlhrHierarchyCode() {
        return alhrHierarchyCode;
    }

    public void setAlhrHierarchyCode(Double alhrHierarchyCode) {
        this.alhrHierarchyCode = alhrHierarchyCode;
    }

    public String getAlnmLocationCode() {
        return alnmLocationCode;
    }

    public void setAlnmLocationCode(String alnmLocationCode) {
        this.alnmLocationCode = alnmLocationCode;
    }

    public String getItmrItemDescription() {
        return itmrItemDescription;
    }

    public void setItmrItemDescription(String itmrItemDescription) {
        this.itmrItemDescription = itmrItemDescription;
    }

    public String getItmrItemCode() {
        return itmrItemCode;
    }

    public void setItmrItemCode(String itmrItemCode) {
        this.itmrItemCode = itmrItemCode;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getAlnmLocationDesc() {
        return alnmLocationDesc;
    }

    public void setAlnmLocationDesc(String alnmLocationDesc) {
        this.alnmLocationDesc = alnmLocationDesc;
    }

    public String getAcrmCurrCode() {
        return acrmCurrCode;
    }

    public void setAcrmCurrCode(String acrmCurrCode) {
        this.acrmCurrCode = acrmCurrCode;
    }

    public Double getRspeSellpPrice() {
        return rspeSellpPrice;
    }

    public void setRspeSellpPrice(Double rspeSellpPrice) {
        this.rspeSellpPrice = rspeSellpPrice;
    }

    public String getRspeSellpActiveFrom() {
        return rspeSellpActiveFrom;
    }

    public void setRspeSellpActiveFrom(String rspeSellpActiveFrom) {
        this.rspeSellpActiveFrom = rspeSellpActiveFrom;
    }

    public String getRspeSellpNReviewDate() {
        return rspeSellpNReviewDate;
    }

    public void setRspeSellpNReviewDate(String rspeSellpNReviewDate) {
        this.rspeSellpNReviewDate = rspeSellpNReviewDate;
    }

    public String getRspeCreationDate() {
        return rspeCreationDate;
    }

    public void setRspeCreationDate(String rspeCreationDate) {
        this.rspeCreationDate = rspeCreationDate;
    }

    public String getRspeLastUpdateDate() {
        return rspeLastUpdateDate;
    }

    public void setRspeLastUpdateDate(String rspeLastUpdateDate) {
        this.rspeLastUpdateDate = rspeLastUpdateDate;
    }

    public String getRspeTaxInclude() {
        return rspeTaxInclude;
    }

    public void setRspeTaxInclude(String rspeTaxInclude) {
        this.rspeTaxInclude = rspeTaxInclude;
    }

    public Double getRspeSellpTaxPercentage() {
        return rspeSellpTaxPercentage;
    }

    public void setRspeSellpTaxPercentage(Double rspeSellpTaxPercentage) {
        this.rspeSellpTaxPercentage = rspeSellpTaxPercentage;
    }

    public Double getRspeSellpWtTax() {
        return rspeSellpWtTax;
    }

    public void setRspeSellpWtTax(Double rspeSellpWtTax) {
        this.rspeSellpWtTax = rspeSellpWtTax;
    }

}
