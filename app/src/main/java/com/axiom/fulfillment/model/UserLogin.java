package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {

    @SerializedName("UserName")
    @Expose
    private String userName;

    @SerializedName("Password")
    @Expose
    private String password;


    @SerializedName("deviceDetails")
    @Expose
    private deviceDetails Deviceinfo;


    public UserLogin(){
    }
    public UserLogin(String name, String Pass){
        this.userName=name;
        this.password=Pass;
    }

    public deviceDetails getDeviceinfo() {
        return Deviceinfo;
    }

    public void setDeviceinfo(deviceDetails deviceinfo) {
        Deviceinfo = deviceinfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}