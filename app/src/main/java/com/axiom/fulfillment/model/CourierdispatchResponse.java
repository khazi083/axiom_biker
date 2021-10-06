package com.axiom.fulfillment.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CourierdispatchResponse {

    @SerializedName("CourierResponse")
    @Expose
    private List<CourierResponse> courierResponse = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<CourierResponse> getCourierResponse() {
        return courierResponse;
    }

    public void setCourierResponse(List<CourierResponse> courierResponse) {
        this.courierResponse = courierResponse;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}