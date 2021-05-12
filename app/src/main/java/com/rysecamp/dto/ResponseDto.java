package com.rysecamp.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by sahil on 10/10/18.
 */

public class ResponseDto<T> implements Serializable {

    private Boolean status;

    private String errorMessage;

    private int statusCode;

    private Long systemDate = Calendar.getInstance().getTime().getTime();

    private T data;

    private String kind;

    public Long getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Long systemDate) {
        this.systemDate = systemDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                ", statusCode=" + statusCode +
                ", systemDate=" + systemDate +
                ", data=" + data +
                ", kind='" + kind + '\'' +
                '}';
    }
}
