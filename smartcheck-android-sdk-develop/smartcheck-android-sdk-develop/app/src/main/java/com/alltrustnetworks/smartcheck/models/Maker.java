package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Maker {
    public String id= "";
    public @Json(name = "aba_number") String abaNumber= "";
    public @Json(name = "account_number") String accountNumber= "";
    public String status= "";
    public String kind= "";
    public String name= "";
    public String address1= "";
    public String address2= "";
    public String city= "";
    public String state= "";
    public String zip= "";
    public String phone= "";
    public @Json(name = "alt_phone") String altPhone= "";
    public String[] notes;

    public Maker() {
    }

    public Maker(Check check) {
        Micr micr = check.getMicr();

        this.setId("");
        this.setAbaNumber(micr.getTransitNumber());
        this.setAccountNumber(micr.getAccountNumber());
        this.setName(check.getPayeeName() != null? check.getPayeeName(): "");

        if (check.getKind() != null) {
            this.setKind(check.getKind());
        }
        else {
            this.setKind(CheckType.PAYROLL);
        }

        this.setAddress1(check.getPayerAddress() != null? check.getPayerAddress(): "");
        this.setNotes(new String[0]);
        this.setStatus(MakerStatus.NEUTRAL);
        this.setName(check.getPayerName());
    }

    public Maker(String id, String abaNumber, String accountNumber, String status, String kind, String name, String address1, String address2, String city, String state, String zip, String phone, String altPhone, String[] notes) {
        this.id = id;
        this.abaNumber = abaNumber;
        this.accountNumber = accountNumber;
        this.status = status;
        this.kind = kind;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.altPhone = altPhone;
        this.notes = notes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAltPhone() {
        return altPhone;
    }

    public void setAltPhone(String altPhone) {
        this.altPhone = altPhone;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Maker{" +
                "id='" + id + '\'' +
                ", abaNumber='" + abaNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", status='" + status + '\'' +
                ", kind='" + kind + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", altPhone='" + altPhone + '\'' +
                '}';
    }
}
