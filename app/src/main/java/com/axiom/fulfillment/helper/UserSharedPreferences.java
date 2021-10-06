package com.axiom.fulfillment.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class UserSharedPreferences {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "axiom.dat";
    private static final String KEY_FIRST_NAME = "firstname";
    private static final String KEY_TOKEN_EXPIRY = "tokonexpiry";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_USER_EMAIL_ID = "email";
    private static final String ACCESS_TOKEN = "token";
    private static final String ACCESS_TOKEN_POD = "tokenpod";
    private static final String KEY_ROLE_ID = "roleId";
    private static final String KEY_USER_CODE = "usercode";
    private static final String KEY_TOKEN_TIME = "tokentime";
    private static final String KEY_EMP_CODE = "hrempcode";
    private static final String KEY_USER_ROLE = "userrole";
    private static final String KEY_FCM= "fcmkey";
    private static final String KEY_COUNTRY_CODE= "countrycode";


    public String getKeyCountryCode() {
        return pref.getString(KEY_COUNTRY_CODE, "");
    }

    public void setKeyCountryCode(String countryCode) {
        editor.putString(KEY_COUNTRY_CODE, countryCode);
        editor.commit();
    }

    public String getKeyUserRole() {
        return pref.getString(KEY_USER_ROLE, "");
    }

    public void setKeyUserRole(String userRole) {
        editor.putString(KEY_USER_ROLE, userRole);
        editor.commit();
    }

    public String getKeyEmpCode() {
        return pref.getString(KEY_EMP_CODE, "");
    }

    public void setKeyEmpCode(String empcode) {
        editor.putString(KEY_EMP_CODE, empcode);
        editor.commit();
    }

    public String getKeyUserCode() {
        return pref.getString(KEY_USER_CODE, "");
    }

    public void setKeyUserCode(String code) {
        editor.putString(KEY_USER_CODE, code);
        editor.commit();
    }

    @SuppressLint("CommitPrefEdits")
    public UserSharedPreferences(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setRoleId(int roleid) {
        editor.putInt(KEY_ROLE_ID, roleid);
        editor.commit();
    }

    public int getRoleId() {
        return pref.getInt(KEY_ROLE_ID, -1);
    }

    public void setFirstName(String firstName) {
        editor.putString(KEY_FIRST_NAME, firstName);
        editor.commit();
    }

    public String getFirstName() {
        return pref.getString(KEY_FIRST_NAME, "");
    }

    public void setKeyTokenExpiry(String expiry) {
        editor.putString(KEY_TOKEN_EXPIRY, expiry);
        editor.commit();
    }

    public String getKeyTokenExpiry() {
        return pref.getString(KEY_TOKEN_EXPIRY, "");
    }


    public void setKeyTokenTime(String tokentime) {
        editor.putString(KEY_TOKEN_TIME, tokentime);
        editor.commit();
    }

    public String getKeyTokenTime() {
        return pref.getString(KEY_TOKEN_TIME, "");
    }

    public void setAccessToken(String token) {
        editor.putString(ACCESS_TOKEN, token);
        editor.commit();
    }

    public void setAccessTokenPod(String token) {
        editor.putString(ACCESS_TOKEN_POD, token);
        editor.commit();
    }

    public String getAccessTokenPod() {
        return pref.getString(ACCESS_TOKEN_POD, "");
    }

    public String getAccessToken() {
        return pref.getString(ACCESS_TOKEN, "");
    }

    public void setUserId(int userId) {
        editor.putInt(KEY_USER_ID, userId);
        editor.commit();
    }

    public void setEmailId(String emailId) {
        editor.putString(KEY_USER_EMAIL_ID, emailId);
        editor.commit();
    }

    public String getEmailId() {
        return pref.getString(KEY_USER_EMAIL_ID, "");
    }

    public int getUserId() {
        return pref.getInt(KEY_USER_ID, 0);
    }


    public String getfcmkey() {
        return pref.getString(KEY_FCM, "");
    }

    public void setfcmkey(String token) {
        editor.putString(KEY_FCM, token);
        editor.commit();
    }

    public void clearSession() {
        editor.clear();
        editor.commit();
    }

}