package com.alltrustnetworks.smartcheck.models;

import com.alltrustnetworks.smartcheck.BuildConfig;
import com.alltrustnetworks.smartcheck.SmartCheckApi;
import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CustomerRequest {
    public @Json(name = "location_id") String locationId;
    public @Json(name = "first_name") String firstName;
    public @Json(name = "middle_name") String middleName;
    public @Json(name = "last_name") String lastName;
    public @Json(name = "date_of_birth") String dateOfBirth;
    public String gender;
    public @Json(name = "cell_phone") String cellPhone;
    public @Json(name = "home_phone") String homePhone;
    public String email;
    public String ssn;
    public String status;
    public Address address;
    public @Json(name = "loyalty_number") LoyaltyNumber loyaltyNumber;
    public @Json(name = "customer_identification") CustomerIdentification customerIdentification;
    public @Json(name = "rec_marketing_messages") Boolean recMarketingMessages;
    public @Json(name = "no_duplicate_check") Boolean noDuplicateCheck;
    public @Json(name = "customer_image") Image customerImage;

    public CustomerRequest() {
    }

    public CustomerRequest(String firstName, String middleName, String lastName,
                           String dateOfBirth, String gender, String cellPhone, String homePhone, String email, String ssn,
                           String status, Address address, LoyaltyNumber loyaltyNumber, CustomerIdentification customerIdentification,
                           Boolean recMarketingMessages, Boolean noDuplicateCheck) {
        this.locationId = SmartCheckApi.getInstance().getLocationId();
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.ssn = ssn;
        this.status = status;
        this.address = address;
        this.loyaltyNumber = loyaltyNumber;
        this.customerIdentification = customerIdentification;
        this.recMarketingMessages = recMarketingMessages;
        this.noDuplicateCheck = noDuplicateCheck;
    }

    public CustomerRequest( String firstName, String middleName,
                            String lastName, String dateOfBirth, String gender,
                            String cellPhone, String homePhone, String email, String ssn, String status,
                            Address address, LoyaltyNumber loyaltyNumber,
                            CustomerIdentification customerIdentification,
                            Boolean recMarketingMessages, Image customerImage) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.cellPhone = cellPhone;
        this.homePhone = homePhone;
        this.email = email;
        this.ssn = ssn;
        this.status = status;
        this.address = address;
        this.loyaltyNumber = loyaltyNumber;
        this.customerIdentification = customerIdentification;
        this.recMarketingMessages = recMarketingMessages;
        this.noDuplicateCheck = true;
        this.customerImage = customerImage;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LoyaltyNumber getLoyaltyNumber() {
        return loyaltyNumber;
    }

    public void setLoyaltyNumber(LoyaltyNumber loyaltyNumber) {
        this.loyaltyNumber = loyaltyNumber;
    }

    public CustomerIdentification getCustomerIdentification() {
        return customerIdentification;
    }

    public void setCustomerIdentification(CustomerIdentification customerIdentification) {
        this.customerIdentification = customerIdentification;
    }

    public Boolean getRecMarketingMessages() {
        return recMarketingMessages;
    }

    public void setRecMarketingMessages(Boolean recMarketingMessages) {
        this.recMarketingMessages = recMarketingMessages;
    }

    public Boolean getNoDuplicateCheck() {
        return noDuplicateCheck;
    }

    public void setNoDuplicateCheck(Boolean noDuplicateCheck) {
        this.noDuplicateCheck = noDuplicateCheck;
    }

    public Image getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(Image customerImage) {
        this.customerImage = customerImage;
    }

    @Override
    public String toString() {
        return "CustomerRequest{" +
                "locationId='" + locationId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", email='" + email + '\'' +
                ", ssn='" + ssn + '\'' +
                ", status='" + status + '\'' +
                ", address=" + address +
                ", loyaltyNumber=" + loyaltyNumber +
                ", customerIdentification=" + customerIdentification +
                ", recMarketingMessages=" + recMarketingMessages +
                ", noDuplicateCheck=" + noDuplicateCheck +
                ", customerImage=" + customerImage +
                '}';
    }
}
