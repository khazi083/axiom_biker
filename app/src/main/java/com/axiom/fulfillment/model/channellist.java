package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class channellist {
    @SerializedName("Channels")
    @Expose
    private List<org_Channels> channels = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<org_Channels> getChannels() {
        return channels;
    }

    public void setChannels(List<org_Channels> channels) {
        this.channels = channels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}