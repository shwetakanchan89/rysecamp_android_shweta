package com.rysecamp.model;

public class SendOtp
{
    public String phone;

    public SendOtp(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
