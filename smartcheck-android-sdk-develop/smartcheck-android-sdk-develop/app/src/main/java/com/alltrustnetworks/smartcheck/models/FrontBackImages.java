package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class FrontBackImages {

    public @Json(name = "front_image")
    String frontImage;
    public @Json(name = "front_image_type")
    String frontImageType;
    public @Json(name = "back_image")
    String backImage;
    public @Json(name = "back_image_type")
    String backImageType;

    public FrontBackImages() {
    }

    public FrontBackImages(String frontImage, String frontImageType, String backImage, String backImageType) {
        this.frontImage = frontImage;
        this.frontImageType = frontImageType;
        this.backImage = backImage;
        this.backImageType = backImageType;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getFrontImageType() {
        return frontImageType;
    }

    public void setFrontImageType(String frontImageType) {
        this.frontImageType = frontImageType;
    }

    public String getBackImage() {
        return backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getBackImageType() {
        return backImageType;
    }

    public void setBackImageType(String backImageType) {
        this.backImageType = backImageType;
    }

    @Override
    public String toString() {
        return "FrontBackImages{" +
                "frontImage='" + frontImage + '\'' +
                ", frontImageType='" + frontImageType + '\'' +
                ", backImage='" + backImage + '\'' +
                ", backImageType='" + backImageType + '\'' +
                '}';
    }
}