package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class UpdateEmailProfileRequest {
    public @Json(name = "username") String username = "";
    public @Json(name = "new_username") String newUsername = "";
    public @Json(name = "customer_id") String customerId = "";
    public @Json(name = "cell_phone") String cell_phone = "";
    public @Json(name = "password") String password = "";
    public UpdateEmailProfileRequest() {
    }

    public UpdateEmailProfileRequest(String username, String newUsername,
                                     String customerId, String cell_phone,
                                     String password) {
        this.username = username.trim();
        this.newUsername = newUsername.trim();
        this.customerId = customerId;
        this.cell_phone = cell_phone;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UpdateEmailProfileRequest{" +
                "username='" + username + '\'' +
                ", newUsername='" + newUsername + '\'' +
                ", customerId='" + customerId + '\'' +
                ", cell_phone='" + cell_phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
