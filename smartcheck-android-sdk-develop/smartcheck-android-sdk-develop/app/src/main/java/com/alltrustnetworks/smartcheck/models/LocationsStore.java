package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class LocationsStore {

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

    public @Json(name = "latitude")
    double latitude;

    public @Json(name = "longitude")
    double longitude;

    public @Json(name = "distance_in_miles")
    double distance_in_miles;

    public LocationsStore() {
    }

    public LocationsStore(String id, String business_id, String location_group_id, String name, String address1, String address2, String city, String state, String zip, String phone, double latitude, double longitude, double distance_in_miles) {
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
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance_in_miles() {
        return (double) Math.round(distance_in_miles * 10) / 10;
    }

    public void setDistance_in_miles(double distance_in_miles) {
        this.distance_in_miles = distance_in_miles;
    }

    @Override
    public String toString() {
        return "LocationsStore{" +
                "id='" + id + '\'' +
                ", business_id='" + business_id + '\'' +
                ", location_group_id='" + location_group_id + '\'' +
                ", name='" + name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", distance_in_miles='" + distance_in_miles + '\'' +
                '}';
    }
}
