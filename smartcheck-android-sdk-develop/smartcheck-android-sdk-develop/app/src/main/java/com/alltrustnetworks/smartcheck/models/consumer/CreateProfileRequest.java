package com.alltrustnetworks.smartcheck.models.consumer;

public class CreateProfileRequest {
    public String cell_phone;
    public String device_name;
    public String email;
    public String serial;

    public CreateProfileRequest(String cell_phone, String device_name, String email, String serial) {
        this.cell_phone = cell_phone;
        this.device_name = device_name;
        this.email = email;
        this.serial = serial;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
