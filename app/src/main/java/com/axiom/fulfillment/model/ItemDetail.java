package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemDetail {
    @SerializedName("ItmrItemCode")
    @Expose
    private String itmrItemCode;
    @SerializedName("ItmrItemDescription")
    @Expose
    private String itmrItemDescription;

    public String getItmrItemCode() {
        return itmrItemCode;
    }

    public void setItmrItemCode(String itmrItemCode) {
        this.itmrItemCode = itmrItemCode;
    }

    public String getItmrItemDescription() {
        return itmrItemDescription;
    }

    public void setItmrItemDescription(String itmrItemDescription) {
        this.itmrItemDescription = itmrItemDescription;
    }
}
