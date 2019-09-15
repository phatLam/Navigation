package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class InfoBusiness {
      @Json(name = "id") public String id;
      @Json(name = "name") public String name;
      @Json(name = "address1") public String address1;
      @Json(name = "address2") public String address2;
      @Json(name = "city") public String city;
      @Json(name = "state") public String state;
      @Json(name = "zip") public String zip;
      @Json(name = "phone") public String phone;
      @Json(name = "email") public String email;
      @Json(name = "contact_name") public String contact_name;
      @Json(name = "contact_phone") public String contact_phone;
      @Json(name = "contact_email") public String contact_email;
      @Json(name = "active") public boolean active;
      @Json(name = "timezone") public String timezone;
      @Json(name = "customer_support") public String customer_support;
}
