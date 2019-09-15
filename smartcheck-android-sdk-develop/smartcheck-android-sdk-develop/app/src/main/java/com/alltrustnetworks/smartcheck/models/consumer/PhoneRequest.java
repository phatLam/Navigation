package com.alltrustnetworks.smartcheck.models.consumer;


public class PhoneRequest {
        private String cell_phone;
        private String token;

    public PhoneRequest(String cell_phone, String token) {
        this.cell_phone = cell_phone;
        this.token = token;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
