package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CustomerIdentification {
    public String id;
    public @Json(name = "customer_id") String customerId;
    public @Json(name = "id_type") String idType;
    public @Json(name = "id_number") String idNumber;
    public String issuer;
    public @Json(name = "expiration_date") String expirationDate;
    public @Json(name = "front_image") Image frontImage;
    public @Json(name = "back_image") Image backImage;

    public CustomerIdentification() {
    }

    public CustomerIdentification(String id, String customerId, String idType, String idNumber, String issuer, String expirationDate, Image frontImage, Image backImage) {
        this.id = id;
        this.customerId = customerId;
        this.idType = idType;
        this.idNumber = idNumber;
        this.issuer = issuer;
        this.expirationDate = expirationDate;
        this.frontImage = frontImage;
        this.backImage = backImage;
    }

    public CustomerIdentification(String id, String customerId, String idType, String idNumber, String issuer, String expirationDate) {
        this.id = id;
        this.customerId = customerId;
        this.idType = idType;
        this.idNumber = idNumber;
        this.issuer = issuer;
        this.expirationDate = expirationDate;
        this.frontImage = new Image("");
        this.backImage = new Image("");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Image frontImage) {
        this.frontImage = frontImage;
    }

    public Image getBackImage() {
        return backImage;
    }

    public void setBackImage(Image backImage) {
        this.backImage = backImage;
    }

    @Override
    public String toString() {
        return "CustomerIdentification{" +
                "id='" + id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", issuer='" + issuer + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", frontImage=" + frontImage +
                ", backImage=" + backImage +
                '}';
    }
}
