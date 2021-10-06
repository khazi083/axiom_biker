package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockList {

    @SerializedName("Stocks")
    @Expose
    private List<Stockitem> Stocks = null;
    @SerializedName("Status")
    @Expose
    private Status status;

    public List<Stockitem> getStocks() {
        return Stocks;
    }

    public void setStocks(List<Stockitem> channels) {
        this.Stocks = channels;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
