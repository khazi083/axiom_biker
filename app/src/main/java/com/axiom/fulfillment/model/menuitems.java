package com.axiom.fulfillment.model;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class menuitems {

    @SerializedName("Menu")
    @Expose
    private List<menu> menu = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<menu> getMenu() {
        return menu;
    }

    public void setMenu(List<menu> menu) {
        this.menu = menu;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}