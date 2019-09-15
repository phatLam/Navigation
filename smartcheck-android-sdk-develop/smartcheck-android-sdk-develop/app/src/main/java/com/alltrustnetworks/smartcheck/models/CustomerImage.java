package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CustomerImage {
    public String id;
    public @Json(name = "customer_id") String customer_id;
    public @Json(name = "image_type") String imageType;
    public String image;

    public CustomerImage() {
    }

    public CustomerImage(String id, String customer_id, String imageType, String image) {
        this.id = id;
        this.customer_id = customer_id;
        this.imageType = imageType;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "CustomerImage{" +
                "id='" + id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", imageType='" + imageType + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
