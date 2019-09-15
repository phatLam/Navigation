package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class ConsumerFormEnrollment {
    @Json(name = "cell_phone") public String cell_phone;
    @Json(name = "username") public String username;

    @Json(name = "customer_id") public String customer_id = "";
    public ConsumerFormEnrollment() {
    }

    public ConsumerFormEnrollment(String cell_phone, String username,
                                  String customer_id) {
        this.cell_phone = cell_phone;
        this.username = username;
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
        this.username = username;
    }
}
