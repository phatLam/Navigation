package com.alltrustnetworks.smartcheck.models.consumer;

public class ForgotPasswordRequest {

    public String username;
    public String new_password;

    public ForgotPasswordRequest(String username, String new_password) {
        this.username = username;
        this.new_password = new_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNewPassword() {
        return new_password;
    }

    public void setNewPassword(String new_password) {
        this.new_password = new_password;
    }
}
