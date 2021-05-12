package com.rysecamp.model;

import com.google.gson.annotations.SerializedName;

public class VerifyOtp
{
    @SerializedName("otp")
     private String otp;
    @SerializedName("user_id")
    private String user_id;

    public VerifyOtp(String otp, String user_id) {
        this.otp = otp;
        this.user_id = user_id;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
