package com.alltrustnetworks.smartcheck.models;

import com.alltrustnetworks.smartcheck.util.Util;
import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class IDOcrResult {
    public @Json(name = "IDNumber") String IdNumber;
    public @Json(name = "DOB") String DOB;
    public @Json(name = "Issued") String Issued;
    public @Json(name = "Expiration") String Expiration;
    public @Json(name = "LastName") String LastName;
    public @Json(name = "FirstName") String FirstName;
    public @Json(name = "MiddleName") String MiddleName;
    public @Json(name = "City") String City;
    public @Json(name = "State") String State;
    public @Json(name = "Zip") String Zip;
    public @Json(name = "Address") String Address;
    public @Json(name = "Class") String Klass;
    public @Json(name = "Gender") String Gender;
    public @Json(name = "Height") String Height;
    public @Json(name = "Eyes") String Eyes;
    public @Json(name = "Hair") String Hair;
    public @Json(name = "Weight") String Weight;
    public @Json(name = "Country") String Country;
    public @Json(name = "IDType") String IDType;
    public @Json(name = "Issuer") String issuer;

    public IDOcrResult() {
    }

    public IDOcrResult(String IdNumber, String DOB, String issued, String expiration, String lastName, String firstName, String middleName, String city, String state, String zip, String address, String klass, String gender, String height, String eyes, String hair, String weight, String country, String IDType) {
        this.IdNumber = IdNumber;
        this.DOB = DOB;
        Issued = issued;
        Expiration = expiration;
        LastName = lastName;
        FirstName = firstName;
        MiddleName = middleName;
        City = city;
        State = state;
        Zip = zip;
        Address = address;
        Klass = klass;
        Gender = gender;
        Height = height;
        Eyes = eyes;
        Hair = hair;
        Weight = weight;
        Country = country;
        this.IDType = IDType;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String IdNumber) {
        this.IdNumber = IdNumber;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getIssued() {
        return Issued;
    }

    public void setIssued(String issued) {
        Issued = issued;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        Expiration = expiration;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = Util.camelCase(lastName," ");
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = Util.camelCase(firstName," ");
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName =  Util.camelCase(middleName," ");
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = Util.camelCase(city," ");
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = Util.camelCase(state," ");
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = Util.camelCase(address, " ");
    }

    public String getKlass() {
        return Klass;
    }

    public void setClass(String Klass) {
        this.Klass = Klass;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getHeight() {
        return Height;
    }

    public void setHeight(String height) {
        Height = height;
    }

    public String getEyes() {
        return Eyes;
    }

    public void setEyes(String eyes) {
        Eyes = eyes;
    }

    public String getHair() {
        return Hair;
    }

    public void setHair(String hair) {
        Hair = hair;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = Util.camelCase(country," ");
    }

    public String getIDType() {
        return IDType;
    }

    public void setIDType(String IDType) {
        this.IDType = IDType;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "IDOcrResult{" +
                "IdNumber='" + IdNumber + '\'' +
                ", DOB='" + DOB + '\'' +
                ", Issued='" + Issued + '\'' +
                ", Expiration='" + Expiration + '\'' +
                ", LastName='" + LastName + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", City='" + City + '\'' +
                ", State='" + State + '\'' +
                ", Zip='" + Zip + '\'' +
                ", Address='" + Address + '\'' +
                ", Klass='" + Klass + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Height='" + Height + '\'' +
                ", Eyes='" + Eyes + '\'' +
                ", Hair='" + Hair + '\'' +
                ", Weight='" + Weight + '\'' +
                ", Country='" + Country + '\'' +
                ", IDType='" + IDType + '\'' +
                ", issuer='" + issuer + '\'' +
                '}';
    }
}
