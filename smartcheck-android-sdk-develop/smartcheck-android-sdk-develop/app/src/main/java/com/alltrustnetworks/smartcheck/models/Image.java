package com.alltrustnetworks.smartcheck.models;

import com.squareup.moshi.Json;

import org.parceler.Parcel;

@Parcel
public class Image {
    public @Json(name = "image_type") String imageType;
    public @Json(name = "image") String image;

    public Image() {
    }

    public Image(String imageType, String image) {
        this.imageType = imageType;
        this.image = image;
    }

    public Image(String image) {
        this.imageType = "jpeg";
        this.image = image;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageType='" + imageType + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
