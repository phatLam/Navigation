package com.alltrustnetworks.smartcheck.models.consumer;

public class EmailRequest {
    private String username = "";

    public EmailRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
