package com.alltrustnetworks.smartcheck.models.consumer;

public class SigninWithTemporaryPasswordRequest {

    public String username;
    public String password;

    public SigninWithTemporaryPasswordRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
