package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachements {
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("OabodId")
    @Expose
    private Integer oabodId;
    @SerializedName("OaboId")
    @Expose
    private String oaboId;
    @SerializedName("OadbId")
    @Expose
    private String oadbId;
    @SerializedName("ObohOrderNo")
    @Expose
    private String obohOrderNo;
   // @SerializedName("OabodFile")
   @SerializedName("OabodFile")
    @Expose
    private String oabodFile;
    @SerializedName("OabodFileName")
    @Expose
    private String oabodFileName;
    @SerializedName("OabodFileType")
    @Expose
    private String oabodFileType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOabodId() {
        return oabodId;
    }

    public void setOabodId(Integer oabodId) {
        this.oabodId = oabodId;
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

    public String getObohOrderNo() {
        return obohOrderNo;
    }

    public void setObohOrderNo(String obohOrderNo) {
        this.obohOrderNo = obohOrderNo;
    }

    public String getOabodFile() {
        return oabodFile;
    }

    public void setOabodFile(String oabodFile) {
        this.oabodFile = oabodFile;
    }

    public String getOabodFileName() {
        return oabodFileName;
    }

    public void setOabodFileName(String oabodFileName) {
        this.oabodFileName = oabodFileName;
    }

    public String getOabodFileType() {
        return oabodFileType;
    }

    public void setOabodFileType(String oabodFileType) {
        this.oabodFileType = oabodFileType;
    }

}
