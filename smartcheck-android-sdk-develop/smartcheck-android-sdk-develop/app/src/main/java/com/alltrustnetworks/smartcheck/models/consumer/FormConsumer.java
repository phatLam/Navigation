package com.alltrustnetworks.smartcheck.models.consumer;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class FormConsumer {
    @Json(name = "id") public int id;
    @Json(name = "cell_phone") public String cell_phone;
    @Json(name = "email") public String email;
    @Json(name = "consumer_name") public String consumer_name;

    public FormConsumer() {
    }

    public FormConsumer(int id, String cell_phone, String email, String consumer_name) {
        this.id = id;
        this.cell_phone = cell_phone;
        this.email = email;
        this.consumer_name = consumer_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }
}
