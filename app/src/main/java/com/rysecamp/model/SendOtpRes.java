package com.rysecamp.model;

import com.google.gson.annotations.SerializedName;

public class SendOtpRes
{
    @SerializedName("success")
    private boolean success;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("redirect")
    private String redirect;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }
}
