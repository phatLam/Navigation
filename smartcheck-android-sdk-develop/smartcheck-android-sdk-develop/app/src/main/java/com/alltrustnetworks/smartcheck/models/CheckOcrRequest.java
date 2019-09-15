package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CheckOcrRequest {
    public @Json(name = "aba_number") String abaNumber;
    public @Json(name = "account_number") String accountNumber;
    public @Json(name = "check_number") String checkNumber;
    public @Json(name = "front_image") String frontImage;
    public @Json(name = "back_image") String backImage;
    public int amount;

    public CheckOcrRequest(String abaNumber, String accountNumber, String checkNumber, String frontImage, String backImage, int amount) {
        this.abaNumber = abaNumber;
        this.accountNumber = accountNumber;
        this.checkNumber = checkNumber;
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.amount = amount;
    }

    public CheckOcrRequest() {

    }

    public String getAbaNumber() {
        return abaNumber;
    }

    public void setAbaNumber(String abaNumber) {
        this.abaNumber = abaNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CheckOcrRequest{" +
                "abaNumber='" + abaNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", checkNumber='" + checkNumber + '\'' +
                ", frontImage='" + frontImage + '\'' +
                ", backImage='" + backImage + '\'' +
                ", amount=" + amount +
                '}';
    }
}
