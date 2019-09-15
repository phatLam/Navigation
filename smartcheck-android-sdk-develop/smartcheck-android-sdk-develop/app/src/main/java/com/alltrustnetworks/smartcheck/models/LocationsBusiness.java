package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class LocationsBusiness {
    public @Json(name = "id")
    String id;
    public @Json(name = "business_id")
    String business_id;
    public @Json(name = "location_group_id")
    String location_group_id;
    public @Json(name = "name")
    String name;
    public @Json(name = "address1")
    String address1;
    public @Json(name = "address2")
    String address2;
    public @Json(name = "city")
    String city;
    public @Json(name = "state")
    String state;
    public @Json(name = "zip")
    String zip;
    public @Json(name = "phone")
    String phone;
    public @Json(name = "email")
    String email;
    public @Json(name = "contact_name")
    String contact_name;
    public @Json(name = "contact_phone")
    String contact_phone;
    public @Json(name = "contact_email")
    String contact_email;
    public @Json(name = "active")
    boolean active;
    public @Json(name = "timezone")
    String timezone;
    public @Json(name = "geo_data")
    GeoData geo_data;
    public @Json(name = "florida_compliance_settings")
    FloridaComplianceSettings florida_compliance_settings;
    public @Json(name = "guarantee")
    boolean guarantee;
    public @Json(name = "distance_in_miles")
    double distance_in_miles;
    public double getDistance_in_miles() {
        return (double) Math.round(distance_in_miles * 10) / 10;
    }

    public void setDistance_in_miles(double distance_in_miles) {
        this.distance_in_miles = distance_in_miles ;
    }

    public LocationsBusiness() {
    }

    public LocationsBusiness(String id, String business_id, String location_group_id, String name, String address1,
                             String address2, String city, String state, String zip, String phone, String email,
                             String contact_name, String contact_phone, String contact_email, boolean active, String timezone,
                             FloridaComplianceSettings florida_compliance_settings, boolean guarantee, int distance_in_miles) {
        this.id = id;
        this.business_id = business_id;
        this.location_group_id = location_group_id;
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.contact_name = contact_name;
        this.contact_phone = contact_phone;
        this.contact_email = contact_email;
        this.active = active;
        this.timezone = timezone;
        this.florida_compliance_settings = florida_compliance_settings;
        this.guarantee = guarantee;
        this.distance_in_miles = distance_in_miles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getLocation_group_id() {
        return location_group_id;
    }

    public void setLocation_group_id(String location_group_id) {
        this.location_group_id = location_group_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public FloridaComplianceSettings getFlorida_compliance_settings() {
        return florida_compliance_settings;
    }

    public void setFlorida_compliance_settings(FloridaComplianceSettings florida_compliance_settings) {
        this.florida_compliance_settings = florida_compliance_settings;
    }

    public boolean isGuarantee() {
        return guarantee;
    }

    public void setGuarantee(boolean guarantee) {
        this.guarantee = guarantee;
    }
}

