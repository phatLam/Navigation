package com.alltrustnetworks.smartcheck.models.consumer;

public class SignUpRequest {
    public String cell_phone;
    public String username;
    public String password;
    public String device_id;
    public String customer_id;
    public SignUpRequest() {
    }

    public SignUpRequest(String cell_phone, String username, String password, String device_id,
                         String customer_id) {
        this.cell_phone = cell_phone;
        this.username = username.trim();
        this.password = password;
        this.device_id = device_id;
        this.customer_id = customer_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
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
