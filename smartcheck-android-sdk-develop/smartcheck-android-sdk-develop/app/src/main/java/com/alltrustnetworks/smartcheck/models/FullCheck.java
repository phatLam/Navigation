package com.alltrustnetworks.smartcheck.models;

import com.alltrustnetworks.smartcheck.util.Util;
import com.squareup.moshi.Json;
import android.util.Log;
import org.parceler.Parcel;

import java.util.Arrays;

@Parcel
public class FullCheck {

    public String id;
    public Maker maker;
    public @Json(name = "amount") int amount;
    public @Json(name = "check_number") String checkNumber;
    public @Json(name = "check_date") String checkDate;
    public String kind;
    public @Json(name = "raw_micr") String rawMicr;
    public @Json(name = "hand_keyed") boolean handKeyed;
    public @Json(name = "iqa_result") String[] iqaResult;
    public String[] notes;
    public String updated;
    public @Json(name = "display_check_images") FrontBackImages displayCheckImages;
    public @Json(name = "deposit_check_images") FrontBackImages depositCheckImages;
    public @Json(name = "client_data") String clientData;
    public @Json(name = "payee_name") String payee_name;

    public @Json(name = "captured") String captured;
    public void setCaptured(String captured) {
        this.captured = captured;
    }

    public String getPayee_name() {
        return payee_name;
    }

    public void setPayee_name(String payee_name) {
        this.payee_name = payee_name;
    }

    public FullCheck() {
    }

    public FullCheck(Check check) {
        this.setId("");
        this.setNotes(new String[0]);

        this.setCheckDate(check.getCheckDate());
        this.setAmount(check.getAmount());
        this.setCheckNumber(check.getMicr().getCheckNumber());
        this.setRawMicr(Util.parseRawMicr(check.getRawMicr()));
        this.setIqaResult(new String[0]);

        if (check.getKind() != null) {
            this.setKind(check.getKind());
        }
        else {
            this.setKind(CheckType.PAYROLL);
        }
        this.setPayee_name(check.payeeName);
        FrontBackImages displayCheckImages = new FrontBackImages();
        FrontBackImages depositCheckImages = new FrontBackImages();

        if (Check.frontOriginalImage != null && Check.rearOriginalImage != null) {
            Log.i("SmartCheck", "set original image");
            displayCheckImages.setFrontImage(Util.toBase64(Check.frontOriginalImage));
            displayCheckImages.setFrontImageType("jpeg");
            displayCheckImages.setBackImage(Util.toBase64(Check.rearOriginalImage));
            displayCheckImages.setBackImageType("jpeg");
        }

        if (Check.frontProcessedImage != null && Check.rearProcessedImage != null) {
            Log.i("SmartCheck", "set proccessed image");

//            depositCheckImages.setFrontImage(Util.toBase64(Check.frontProcessedImage));
            depositCheckImages.setFrontImage(Check.base64FrontProcessedImage);
            depositCheckImages.setFrontImageType("tiff");
//            depositCheckImages.setBackImage(Util.toBase64(Check.rearProcessedImage));
            depositCheckImages.setBackImage(Check.base64RearProcessedImage);
            depositCheckImages.setBackImageType("tiff");
        }



//        if (Check.frontProcessedImage != null) {
//            Log.i("SmartCheck", "set front image");
//            displayCheckImages.setFrontImage(Util.toBase64(Check.frontProcessedImage));
//            displayCheckImages.setFrontImageType("jpeg");
//            depositCheckImages.setFrontImage(Util.toBase64(Check.frontProcessedImage));
//            depositCheckImages.setFrontImageType("jpeg");
//        }
//
//        if (Check.rearProcessedImage != null) {
//            Log.i("SmartCheck", "set rear image");
//
//            displayCheckImages.setBackImage(Util.toBase64(Check.rearProcessedImage));
//            displayCheckImages.setBackImageType("jpeg");
//            depositCheckImages.setBackImage(Util.toBase64(Check.rearProcessedImage));
//            depositCheckImages.setBackImageType("jpeg");
//        }

        this.setCaptured("mobile");
        this.setDisplayCheckImages(displayCheckImages);
        this.setDepositCheckImages(depositCheckImages);

        Maker maker = new Maker(check);
        this.setMaker(maker);
    }

    public FullCheck(String id, Maker maker, int amount, String checkNumber, String checkDate, String kind, String rawMicr, boolean handKeyed, String[] iqaResult, String[] notes, String updated, FrontBackImages displayCheckImages, FrontBackImages depositCheckImages, String clientData) {
        this.id = id;
        this.maker = maker;
        this.amount = amount;
        this.checkNumber = checkNumber;
        this.checkDate = checkDate;
        this.kind = kind;
        this.rawMicr = rawMicr;
        this.handKeyed = handKeyed;
        this.iqaResult = iqaResult;
        this.notes = notes;
        this.updated = updated;
        this.displayCheckImages = displayCheckImages;
        this.depositCheckImages = depositCheckImages;
        this.clientData = clientData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Maker getMaker() {
        return maker;
    }

    public void setMaker(Maker maker) {
        this.maker = maker;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getRawMicr() {
        return rawMicr;
    }

    public void setRawMicr(String rawMicr) {
        this.rawMicr = rawMicr;
    }

    public boolean isHandKeyed() {
        return handKeyed;
    }

    public void setHandKeyed(boolean handKeyed) {
        this.handKeyed = handKeyed;
    }

    public String[] getIqaResult() {
        return iqaResult;
    }

    public void setIqaResult(String[] iqaResult) {
        this.iqaResult = iqaResult;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public FrontBackImages getDisplayCheckImages() {
        return displayCheckImages;
    }

    public void setDisplayCheckImages(FrontBackImages displayCheckImages) {
        this.displayCheckImages = displayCheckImages;
    }

    public FrontBackImages getDepositCheckImages() {
        return depositCheckImages;
    }

    public void setDepositCheckImages(FrontBackImages depositCheckImages) {
        this.depositCheckImages = depositCheckImages;
    }

    public String getClientData() {
        return clientData;
    }

    public void setClientData(String clientData) {
        this.clientData = clientData;
    }

    @Override
    public String toString() {
        return "FullCheck{" +
                "id='" + id + '\'' +
                ", maker=" + maker +
                ", amount=" + amount +
                ", checkNumber='" + checkNumber + '\'' +
                ", checkDate='" + checkDate + '\'' +
                ", kind='" + kind + '\'' +
                ", rawMicr='" + rawMicr + '\'' +
                ", handKeyed=" + handKeyed +
                ", iqaResult=" + Arrays.toString(iqaResult) +
                ", notes=" + Arrays.toString(notes) +
                ", updated='" + updated + '\'' +
                ", displayCheckImages=" + displayCheckImages +
                ", depositCheckImages=" + depositCheckImages +
                ", clientData='" + clientData + '\'' +
                '}';
    }
}
