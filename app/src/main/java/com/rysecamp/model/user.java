package com.rysecamp.model;

import com.google.gson.annotations.SerializedName;

public class user
{
   @SerializedName("status")
   private int status;
    @SerializedName("_id")
    private String id;
    @SerializedName("phone")
    private String phone;
    @SerializedName("otpHash")
    private String otpHash;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("__v")
    private int v;
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtpHash() {
        return otpHash;
    }

    public void setOtpHash(String otpHash) {
        this.otpHash = otpHash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
