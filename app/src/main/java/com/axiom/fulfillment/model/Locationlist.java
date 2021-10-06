package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Locationlist {

    @SerializedName("LocationMaster")
    @Expose
    private List<LocationMaster> Location = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<LocationMaster> getLocation() {
        return Location;
    }

    public void setLocation(List<LocationMaster> location) {
        Location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
