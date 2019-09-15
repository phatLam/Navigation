package com.alltrustnetworks.smartcheck.models.consumer;

public class SignInRequest {
    public String username;
    public String password;
    public String device_id;

    public SignInRequest() {
    }

    public SignInRequest(String username, String password, String device_id) {
        this.username = username.trim();
        this.password = password;
        this.device_id = device_id;
    }

    public SignInRequest(String username, String device_id) {
        this.username = username.trim();
        this.device_id = device_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
