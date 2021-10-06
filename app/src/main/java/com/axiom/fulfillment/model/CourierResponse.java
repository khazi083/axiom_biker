package com.axiom.fulfillment.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourierResponse {

    @SerializedName("AirwayBillNo")
    @Expose
    private String airwayBillNo;
    @SerializedName("LabelPath")
    @Expose
    private String labelPath;
    @SerializedName("ShipmentData")
    @Expose
    private String shipmentData;
    @SerializedName("AxiomOrderNo")
    @Expose
    private String axiomOrderNo;
    @SerializedName("TrackingLink")
    @Expose
    private String trackingLink;

    public String getAirwayBillNo() {
        return airwayBillNo;
    }

    public void setAirwayBillNo(String airwayBillNo) {
        this.airwayBillNo = airwayBillNo;
    }

    public String getLabelPath() {
        return labelPath;
    }

    public void setLabelPath(String labelPath) {
        this.labelPath = labelPath;
    }

    public String getShipmentData() {
        return shipmentData;
    }

    public void setShipmentData(String shipmentData) {
        this.shipmentData = shipmentData;
    }

    public String getAxiomOrderNo() {
        return axiomOrderNo;
    }

    public void setAxiomOrderNo(String axiomOrderNo) {
        this.axiomOrderNo = axiomOrderNo;
    }

    public String getTrackingLink() {
        return trackingLink;
    }

    public void setTrackingLink(String trackingLink) {
        this.trackingLink = trackingLink;
    }

}
