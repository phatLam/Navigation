package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

import java.util.Arrays;

@Parcel
public class CheckOcrResponse {
    public @Json(name = "micr") String micr;
    public @Json(name = "amount") int amount;
    public @Json(name = "check_date") String check_date;
    public @Json(name = "maker_name1") String maker_name1;
    public @Json(name = "maker_name2") String maker_name2;
    public @Json(name = "address") String address;
    public @Json(name = "city") String city;
    public @Json(name = "state") String state;
    public @Json(name = "zip") String zip;
    public @Json(name = "payeeName") String payeeName;
    public @Json(name = "payeeAddress") String payeeAddress;
    public @Json(name = "payeeCity") String payeeCity;
    public @Json(name = "payeeState") String payeeState;
    public @Json(name = "payeeZip") String payeeZip;
    public @Json(name = "image_quality") String image_quality;
    public @Json(name = "iqa_fail_reasons") String[] iqa_fail_reasons;
    public @Json(name = "usability") String usability;
    public @Json(name = "usability_fail_reasons") String[] usability_fail_reasons;
    public @Json(name = "check_type") String check_type;
    public @Json(name = "error_message") String error_message;

    public CheckOcrResponse() {
    }

    public CheckOcrResponse(String micr, int amount, String check_date, String maker_name1, String maker_name2, String address, String city, String state, String zip, String payeeName, String payeeAddress, String payeeCity, String payeeState, String payeeZip, String image_quality, String[] iqa_fail_reasons, String usability, String[] usability_fail_reasons, String check_type, String error_message) {
        this.micr = micr;
        this.amount = amount;
        this.check_date = check_date;
        this.maker_name1 = maker_name1;
        this.maker_name2 = maker_name2;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.payeeName = payeeName;
        this.payeeAddress = payeeAddress;
        this.payeeCity = payeeCity;
        this.payeeState = payeeState;
        this.payeeZip = payeeZip;
        this.image_quality = image_quality;
        this.iqa_fail_reasons = iqa_fail_reasons;
        this.usability = usability;
        this.usability_fail_reasons = usability_fail_reasons;
        this.check_type = check_type;
        this.error_message = error_message;
    }

    public String getMicr() {
        return micr;
    }

    public void setMicr(String micr) {
        this.micr = micr;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getMaker_name1() {
        return maker_name1;
    }

    public void setMaker_name1(String maker_name1) {
        this.maker_name1 = maker_name1;
    }

    public String getMaker_name2() {
        return maker_name2;
    }

    public void setMaker_name2(String maker_name2) {
        this.maker_name2 = maker_name2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeAddress() {
        return payeeAddress;
    }

    public void setPayeeAddress(String payeeAddress) {
        this.payeeAddress = payeeAddress;
    }

    public String getPayeeCity() {
        return payeeCity;
    }

    public void setPayeeCity(String payeeCity) {
        this.payeeCity = payeeCity;
    }

    public String getPayeeState() {
        return payeeState;
    }

    public void setPayeeState(String payeeState) {
        this.payeeState = payeeState;
    }

    public String getPayeeZip() {
        return payeeZip;
    }

    public void setPayeeZip(String payeeZip) {
        this.payeeZip = payeeZip;
    }

    public String getImage_quality() {
        return image_quality;
    }

    public void setImage_quality(String image_quality) {
        this.image_quality = image_quality;
    }

    public String[] getIqa_fail_reasons() {
        return iqa_fail_reasons;
    }

    public void setIqa_fail_reasons(String[] iqa_fail_reasons) {
        this.iqa_fail_reasons = iqa_fail_reasons;
    }

    public String getUsability() {
        return usability;
    }

    public void setUsability(String usability) {
        this.usability = usability;
    }

    public String[] getUsability_fail_reasons() {
        return usability_fail_reasons;
    }

    public void setUsability_fail_reasons(String[] usability_fail_reasons) {
        this.usability_fail_reasons = usability_fail_reasons;
    }

    public String getCheck_type() {
        return check_type;
    }

    public void setCheck_type(String check_type) {
        this.check_type = check_type;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return "CheckOcrResponse{" +
                "micr='" + micr + '\'' +
                ", amount=" + amount +
                ", check_date='" + check_date + '\'' +
                ", maker_name1='" + maker_name1 + '\'' +
                ", maker_name2='" + maker_name2 + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", payeeAddress='" + payeeAddress + '\'' +
                ", payeeCity='" + payeeCity + '\'' +
                ", payeeState='" + payeeState + '\'' +
                ", payeeZip='" + payeeZip + '\'' +
                ", image_quality='" + image_quality + '\'' +
                ", iqa_fail_reasons=" + Arrays.toString(iqa_fail_reasons) +
                ", usability='" + usability + '\'' +
                ", usability_fail_reasons=" + Arrays.toString(usability_fail_reasons) +
                ", check_type='" + check_type + '\'' +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
