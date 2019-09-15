package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class CustomerIdentificationOCR {
    public @Json(name = "front_image") Image frontImage;
    public @Json(name = "back_image") Image backImage;
    public @Json(name = "resolution") int resolution;

    public CustomerIdentificationOCR() {
    }

    public CustomerIdentificationOCR(Image frontImage, Image backImage, int resolution) {
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.resolution = resolution;
    }

    public CustomerIdentificationOCR(Image frontImage, Image backImage) {
        this.frontImage = frontImage;
        this.backImage = backImage;
        this.resolution = 200;
    }

    public Image getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(Image frontImage) {
        this.frontImage = frontImage;
    }

    public Image getBackImage() {
        return backImage;
    }

    public void setBackImage(Image backImage) {
        this.backImage = backImage;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    @Override
    public String toString() {
        return "CustomerIdentificationOCR{" +
                "frontImage='" + frontImage + '\'' +
                "backImage='" + backImage + '\'' +
                "resolution='" + resolution + '\'' +
                '}';
    }
}
