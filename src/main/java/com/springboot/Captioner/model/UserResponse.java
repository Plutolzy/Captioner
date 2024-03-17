package com.springboot.Captioner.model;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    private boolean success;
    private String message;
    @SerializedName("email")
    private String email;

    // 构造函数、getter和setter省略

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

