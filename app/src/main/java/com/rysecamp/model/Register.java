package com.rysecamp.model;

import com.google.gson.annotations.SerializedName;

public class Register
{
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("user_id")
    private String userId;


    public Register(String name, String email, String userId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
