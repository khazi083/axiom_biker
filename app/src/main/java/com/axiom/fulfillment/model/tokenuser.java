package com.axiom.fulfillment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class tokenuser {
    @SerializedName("username")
    @Expose
    String username;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("grant_type")
    @Expose
    String grant_type;

    public tokenuser(){
        this.grant_type="password";
        this.username="a_karani";
        this.password="1234";
    }

    public tokenuser(String user, String pass, String  grant_type){
        this.grant_type="password";
        this.username="a_karani";
        this.password="1234";
    }
}
