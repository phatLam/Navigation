package com.alltrustnetworks.smartcheck.models;

import com.alltrustnetworks.smartcheck.util.Util;
import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Check {

    public double car;
    public int amount;
    public String rawMicr;
    public String payeeName;
    public String status;
    public String error;
    public @Json(name = "check_date") String checkDate;
    public String payerName;
    public String payerAddress;
    public @Json(name = "check_number") String checkNumber;
    public Micr micr;
    public String kind;

    public Boolean getExistAccountNumber() {
        return isExistAccountNumber;
    }

    public void setExistAccountNumber(Boolean existAccountNumber) {
        isExistAccountNumber = existAccountNumber;
    }

    public Boolean isExistAccountNumber;

    public transient static byte[] frontOriginalImage;
    public transient static byte[] frontPreProcessedImage;
    public transient static byte[] frontProcessedImage;

    public transient static byte[] rearOriginalImage;
    public transient static byte[] rearPreProcessedImage;
    public transient static byte[] rearProcessedImage;

    public transient static String base64RearProcessedImage;
    public transient static String base64FrontProcessedImage;

    public Check() {
    }

    public double getCar() {
        return car;
    }

    public void setCar(double car) {
        this.car = car;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getRawMicr() {
        return rawMicr;
    }

    public void setRawMicr(String rawMicr) {
        this.rawMicr = rawMicr;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {

        this.payeeName = Util.removeUnderScore(payeeName);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getPayerAddress() {
        return payerAddress;
    }

    public void setPayerAddress(String payerAddress) {

        this.payerAddress = Util.removeUnderScore(payerAddress);
    }

    public Micr getMicr() {
        return micr;
    }

    public void setMicr(Micr micr) {
        this.micr = micr;
    }

    public static byte[] getFrontOriginalImage() {
        return frontOriginalImage;
    }

    public static void setFrontOriginalImage(byte[] frontOriginalImage) {
        Check.frontOriginalImage = frontOriginalImage;
    }

    public static byte[] getFrontPreProcessedImage() {
        return frontPreProcessedImage;
    }

    public static void setFrontPreProcessedImage(byte[] frontPreProcessedImage) {
        Check.frontPreProcessedImage = frontPreProcessedImage;
    }

    public static byte[] getFrontProcessedImage() {
        return frontProcessedImage;
    }

    public static void setFrontProcessedImage(byte[] frontProcessedImage) {
        Check.frontProcessedImage = frontProcessedImage;
    }

    public static byte[] getRearOriginalImage() {
        return rearOriginalImage;
    }

    public static void setRearOriginalImage(byte[] rearOriginalImage) {
        Check.rearOriginalImage = rearOriginalImage;
    }

    public static byte[] getRearPreProcessedImage() {
        return rearPreProcessedImage;
    }

    public static void setRearPreProcessedImage(byte[] rearPreProcessedImage) {
        Check.rearPreProcessedImage = rearPreProcessedImage;
    }

    public static byte[] getRearProcessedImage() {
        return rearProcessedImage;
    }

    public static void setRearProcessedImage(byte[] rearProcessedImage) {
        Check.rearProcessedImage = rearProcessedImage;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = Util.removeUnderScore(payerName);
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public String toString() {
        return "Check{" +
                "car=" + car +
                ", amount=" + amount +
                ", rawMicr=" + rawMicr +
                ", payeeName=" + payeeName +
                ", status=" + status +
                ", error=" + error  +
                ", checkDate=" + checkDate  +
                ", payerName=" + payerName   +
                ", payerAddress=" + payerAddress  +
                ", micr=" + micr +
                ", kind=" + kind +
                '}';
    }
}
