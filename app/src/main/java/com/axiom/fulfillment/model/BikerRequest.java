package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BikerRequest {

    @SerializedName("OaboId")
    @Expose
    private String oaboId;
    @SerializedName("OadbId")
    @Expose
    private String oadbId;
    @SerializedName("OaboStatus")
    @Expose
    private String oaboStatus;
    @SerializedName("OaboComments")
    @Expose
    private String oaboComments;
    @SerializedName("OaboSatisfied")
    @Expose
    private String oaboSatisfied;
    @SerializedName("OaboSignature")
    @Expose
    private String oaboSignature;

    @SerializedName("OaboSignFileName")
    @Expose
    private String OaboSignFileName;

    @SerializedName("OaboSignFileType")
    @Expose
    private String OaboSignFileType;

    @SerializedName("UserDetails")
    @Expose
    private UserDetails userDetails;

    @SerializedName("OaboBankAuthCode")
    @Expose
    private String OaboBankAuthCode ;

    @SerializedName("OaboUserGps")
    @Expose
    private String OaboUserGps ;

    @SerializedName("DocumentIdType")
    @Expose
    private String DocumentIdType ;

    @SerializedName("DocumentId")
    @Expose
    private String DocumentId ;

    @SerializedName("DocumentHolderName")
    @Expose
    private String DocumentHolderName ;

    public String getDocumentIdType() {
        return DocumentIdType;
    }

    public void setDocumentIdType(String documentIdType) {
        DocumentIdType = documentIdType;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }

    public String getDocumentHolderName() {
        return DocumentHolderName;
    }

    public void setDocumentHolderName(String documentHolderName) {
        DocumentHolderName = documentHolderName;
    }

    public String getOaboUserGps() {
        return OaboUserGps;
    }

    public void setOaboUserGps(String oaboUserGps) {
        OaboUserGps = oaboUserGps;
    }

    public String getOaboBankAuthCode() {
        return OaboBankAuthCode;
    }

    public void setOaboBankAuthCode(String oaboBankAuthCode) {
        OaboBankAuthCode = oaboBankAuthCode;
    }

    public String getOaboSignFileName() {
        return OaboSignFileName;
    }

    public void setOaboSignFileName(String oaboSignFileName) {
        OaboSignFileName = oaboSignFileName;
    }

    public String getOaboSignFileType() {
        return OaboSignFileType;
    }

    public void setOaboSignFileType(String oaboSignFileType) {
        OaboSignFileType = oaboSignFileType;
    }

    public String getOaboId() {
        return oaboId;
    }

    public void setOaboId(String oaboId) {
        this.oaboId = oaboId;
    }

    public String getOadbId() {
        return oadbId;
    }

    public void setOadbId(String oadbId) {
        this.oadbId = oadbId;
    }

    public String getOaboStatus() {
        return oaboStatus;
    }

    public void setOaboStatus(String oaboStatus) {
        this.oaboStatus = oaboStatus;
    }

    public String getOaboComments() {
        return oaboComments;
    }

    public void setOaboComments(String oaboComments) {
        this.oaboComments = oaboComments;
    }

    public String getOaboSatisfied() {
        return oaboSatisfied;
    }

    public void setOaboSatisfied(String oaboSatisfied) {
        this.oaboSatisfied = oaboSatisfied;
    }

    public String getOaboSignature() {
        return oaboSignature;
    }

    public void setOaboSignature(String oaboSignature) {
        this.oaboSignature = oaboSignature;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

}