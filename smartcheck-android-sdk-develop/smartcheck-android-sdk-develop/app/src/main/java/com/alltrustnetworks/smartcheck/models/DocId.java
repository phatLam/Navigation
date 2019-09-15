package com.alltrustnetworks.smartcheck.models;

import org.parceler.Parcel;

@Parcel
public class DocId {
    public String middleName;
    public String firstName;
    public String lastName;
    public String birthDate;
    public String placeOfBirth;
    public String authority;
    public String address;
    public String address2;
    public String city;
    public String country;
    public String zipcode;
    public String state;
    public String gender;
    public String idNumber;
    public String issueDate;
    public String expirationDate;
    public String nationality;
    public String status;
    public String error;
    public String issuer;
    public String idType;
    public String email;
    public String ssn;
    public String cellPhone;
    public transient static byte[] frontProcessedImage;
    public transient static byte[] rearProcessedImage;

    public DocId() {
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public static byte[] getFrontProcessedImage() {
        return frontProcessedImage;
    }

    public static void setFrontProcessedImage(byte[] frontProcessedImage) {
        DocId.frontProcessedImage = frontProcessedImage;
    }

    public static byte[] getRearProcessedImage() {
        return rearProcessedImage;
    }

    public static void setRearProcessedImage(byte[] rearProcessedImage) {
        DocId.rearProcessedImage = rearProcessedImage;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String toString() {
        return "DocId{" +
                "middleName='" + middleName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", authority='" + authority + '\'' +
                ", address='" + address + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", gender='" + gender + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", issueDate='" + issueDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", nationality='" + nationality + '\'' +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", issuer='" + issuer + '\'' +
                ", idType='" + idType + '\'' +
                ", email='" + email + '\'' +
                ", ssn='" + ssn + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                '}';
    }
}
