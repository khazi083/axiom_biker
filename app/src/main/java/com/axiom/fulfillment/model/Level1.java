package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Level1 {
    @SerializedName("ObohOrderSource")
    @Expose
    private String obohOrderSource;
    @SerializedName("SourceCount")
    @Expose
    private Integer sourceCount;

    public String getObohOrderSource() {
        return obohOrderSource;
    }

    public void setObohOrderSource(String obohOrderSource) {
        this.obohOrderSource = obohOrderSource;
    }

    public Integer getSourceCount() {
        return sourceCount;
    }

    public void setSourceCount(Integer sourceCount) {
        this.sourceCount = sourceCount;
    }

}
