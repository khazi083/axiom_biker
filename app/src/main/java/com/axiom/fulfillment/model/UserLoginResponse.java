package com.axiom.fulfillment.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginResponse {

    @SerializedName("UserRole")
    @Expose
    private String UserRole;
    @SerializedName("IsValidDomainUser")
    @Expose
    private Boolean isValidDomainUser;
    @SerializedName("Escalation")
    @Expose
    private String escalation;

    public Boolean getValidDomainUser() {
        return isValidDomainUser;
    }

    public void setValidDomainUser(Boolean validDomainUser) {
        isValidDomainUser = validDomainUser;
    }

    public int getEscalationId() {
        return EscalationId;
    }

    public void setEscalationId(int escalationId) {
        EscalationId = escalationId;
    }

    public Boolean getValiduser() {
        return isValiduser;
    }

    public void setValiduser(Boolean validuser) {
        isValiduser = validuser;
    }

    @SerializedName("EscalationId")
    @Expose
    private int EscalationId;

    @SerializedName("RedirectLink")
    @Expose
    private Object redirectLink;
    @SerializedName("SideMenu")
    @Expose
    private Object sideMenu;
    @SerializedName("UserDetails")
    @Expose
    private List<UserDetails> userDetails = null;
    @SerializedName("IsValiduser")
    @Expose
    private Boolean isValiduser;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        this.UserRole = userRole;
    }

    public Boolean getIsValidDomainUser() {
        return isValidDomainUser;
    }

    public void setIsValidDomainUser(Boolean isValidDomainUser) {
        this.isValidDomainUser = isValidDomainUser;
    }

    public String getEscalation() {
        return escalation;
    }

    public void setEscalation(String escalation) {
        this.escalation = escalation;
    }

    public Object getRedirectLink() {
        return redirectLink;
    }

    public void setRedirectLink(Object redirectLink) {
        this.redirectLink = redirectLink;
    }

    public Object getSideMenu() {
        return sideMenu;
    }

    public void setSideMenu(Object sideMenu) {
        this.sideMenu = sideMenu;
    }

    public List<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(List<UserDetails> userDetails) {
        this.userDetails = userDetails;
    }

    public Boolean getIsValiduser() {
        return isValiduser;
    }

    public void setIsValiduser(Boolean isValiduser) {
        this.isValiduser = isValiduser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}