package com.axiom.fulfillment.model;

import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class organizationList {

    @SerializedName("Status")
    @Expose
    private Status status;
    @SerializedName("Organization")
    @Expose
    private List<Organization> organization = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Organization> getOrganization() {
        return organization;
    }

    public void setOrganization(List<Organization> organization) {
        this.organization = organization;
    }

}