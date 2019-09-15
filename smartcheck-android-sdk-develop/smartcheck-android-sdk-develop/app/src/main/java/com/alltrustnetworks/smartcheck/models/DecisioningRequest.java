package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class DecisioningRequest {

    public @Json(name = "business_id") String businessId = "";
    public @Json(name = "location_group_id") String locationGroupId = "";
    public @Json(name = "location_id") String locationId ="";
    public @Json(name = "customer_id") String customerId= "";
    public int amount;
    public @Json(name = "check_number") String checkNumber= "";
    public @Json(name = "check_date") String checkDate= "";
    public @Json(name = "hand_written") boolean handWritten;
    public @Json(name = "hand_keyed") boolean handKeyed;
    public Maker maker;

    public DecisioningRequest() {
    }

    public DecisioningRequest(Customer customer, Check check, Maker maker, String businessId, String locationId) {
        Micr micr = check.getMicr();
        this.setAmount(check.getAmount());
        this.setBusinessId(businessId);
        this.setLocationId(locationId);
        this.setCustomerId(customer.getId());
        this.setMaker(maker);
        this.setCheckNumber(micr.getCheckNumber());
        this.setCheckDate(check.getCheckDate());
        this.setHandWritten(false);
        this.setHandKeyed(false);
    }

    public DecisioningRequest(String businessId, String locationGroupId, String locationId, String customerId, int amount, String checkNumber, String checkDate, boolean handWritten, boolean handKeyed, Maker maker) {
        this.businessId = businessId;
        this.locationGroupId = locationGroupId;
        this.locationId = locationId;
        this.customerId = customerId;
        this.amount = amount;
        this.checkNumber = checkNumber;
        this.checkDate = checkDate;
        this.handWritten = handWritten;
        this.handKeyed = handKeyed;
        this.maker = maker;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getLocationGroupId() {
        return locationGroupId;
    }

    public void setLocationGroupId(String locationGroupId) {
        this.locationGroupId = locationGroupId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public boolean isHandWritten() {
        return handWritten;
    }

    public void setHandWritten(boolean handWritten) {
        this.handWritten = handWritten;
    }

    public boolean isHandKeyed() {
        return handKeyed;
    }

    public void setHandKeyed(boolean handKeyed) {
        this.handKeyed = handKeyed;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    @Override
    public String toString() {
        return "DecisioningRequest{" +
                "businessId='" + businessId + '\'' +
                ", locationGroupId='" + locationGroupId + '\'' +
                ", locationId='" + locationId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", checkNumber='" + checkNumber + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", handWritten=" + handWritten +
                ", handKeyed=" + handKeyed +
                '}';
    }
}
