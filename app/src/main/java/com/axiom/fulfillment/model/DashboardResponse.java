package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardResponse {

    @SerializedName("Dashboard")
    @Expose
    private List<Dashboard> dashboard = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<Dashboard> getDashboard() {
        return dashboard;
    }

    public void setDashboard(List<Dashboard> dashboard) {
        this.dashboard = dashboard;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
