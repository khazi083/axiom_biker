package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ShipmentReport {
    @SerializedName("report")
    @Expose
    private List<Report> report = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
