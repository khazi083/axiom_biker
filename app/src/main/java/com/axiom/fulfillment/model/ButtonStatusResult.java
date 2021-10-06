package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ButtonStatusResult {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("CanCancel")
    @Expose
    private Boolean canCancel;
    @SerializedName("ShowInvoice")
    @Expose
    private Boolean showInvoice;

    @SerializedName("CanAirwayBill")
    @Expose
    private Boolean CanAirwayBill;
    @SerializedName("ShowAirwayBill")
    @Expose
    private Boolean ShowAirwayBill;

    @SerializedName("ShowAxiomBikerDelivery")
    @Expose
    private Boolean ShowAxiomBikerDelivery;

    @SerializedName("ShowShipmentData")
    @Expose
    private Boolean ShowShipmentData;


    @SerializedName("ShowCourierTracker")
    @Expose
    private Boolean ShowCourierTracker;

    public Boolean getShowCourierTracker() {
        return ShowCourierTracker;
    }

    public void setShowCourierTracker(Boolean showCourierTracker) {
        ShowCourierTracker = showCourierTracker;
    }

    public Boolean getShowShipmentData() {
        return ShowShipmentData;
    }

    public void setShowShipmentData(Boolean showShipmentData) {
        ShowShipmentData = showShipmentData;
    }

    public Boolean getCanAirwayBill() {
        return CanAirwayBill;
    }

    public void setCanAirwayBill(Boolean canAirwayBill) {
        CanAirwayBill = canAirwayBill;
    }

    public Boolean getShowAirwayBill() {
        return ShowAirwayBill;
    }

    public void setShowAirwayBill(Boolean showAirwayBill) {
        ShowAirwayBill = showAirwayBill;
    }

    public Boolean getShowAxiomBikerDelivery() {
        return ShowAxiomBikerDelivery;
    }

    public void setShowAxiomBikerDelivery(Boolean showAxiomBikerDelivery) {
        ShowAxiomBikerDelivery = showAxiomBikerDelivery;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean getCanCancel() {
        return canCancel;
    }

    public void setCanCancel(Boolean canCancel) {
        this.canCancel = canCancel;
    }

    public Boolean getShowInvoice() {
        return showInvoice;
    }

    public void setShowInvoice(Boolean showInvoice) {
        this.showInvoice = showInvoice;
    }
}