package com.axiom.fulfillment.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class userrolesList {

    @SerializedName("roles")
    @Expose
    private List<userrole> roles = null;
//    @SerializedName("Status")
//    @Expose
//    private Status status;

    public List<userrole> getRoles() {
        return roles;
    }

    public void setRoles(List<userrole> roles) {
        this.roles = roles;
    }

//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

}