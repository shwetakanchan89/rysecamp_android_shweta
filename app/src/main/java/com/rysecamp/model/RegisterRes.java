package com.rysecamp.model;

import com.google.gson.annotations.SerializedName;

public class RegisterRes
{
    @SerializedName("success")
   private boolean success;
    @SerializedName("msg")
    private String msg;
    @SerializedName("auth_token")
    private String authToken;

    private user user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public com.rysecamp.model.user getUser() {
        return user;
    }

    public void setUser(com.rysecamp.model.user user) {
        this.user = user;
    }
}
