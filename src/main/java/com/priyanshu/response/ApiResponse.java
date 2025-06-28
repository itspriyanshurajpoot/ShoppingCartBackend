package com.priyanshu.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;


public class ApiResponse {

    private String message;
    private boolean success;
    private Object data;
    private String jwtToken;


    public ApiResponse() {
    }

    public ApiResponse(String message, boolean success, Object data, String jwtToken) {
        this.message = message;
        this.success = success;
        this.data = data;
        this.jwtToken = jwtToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
