package com.alltrustnetworks.smartcheck.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.alltrustnetworks.smartcheck.MisnapActivity;
import com.alltrustnetworks.smartcheck.DelayedProgressDialog;
import com.alltrustnetworks.smartcheck.MisnapApi;
import com.alltrustnetworks.smartcheck.SmartCheckActivity;
import com.alltrustnetworks.smartcheck.SmartCheckApi;
import com.alltrustnetworks.smartcheck.models.Check;
import com.alltrustnetworks.smartcheck.models.CheckOcrRequest;
import com.alltrustnetworks.smartcheck.models.CheckOcrResponse;
import com.alltrustnetworks.smartcheck.models.Maker;
import com.alltrustnetworks.smartcheck.models.Micr;
import com.alltrustnetworks.smartcheck.models.misnap.Transaction;
import com.alltrustnetworks.smartcheck.models.misnap.TransactionData;
import com.alltrustnetworks.smartcheck.util.Util;
import com.miteksystems.misnap.events.OnFrameProcessedEvent;
import com.miteksystems.misnap.params.MiSnapAPI;

import org.parceler.Parcels;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageProcessor extends AsyncTask<Void, Void, Check>
{

    private MisnapActivity activity;
    private MisnapApi misnapApi;
    private SmartCheckApi api;
    private DelayedProgressDialog progressDialog;
    private Bundle extras;
    private Check check;

    public ImageProcessor(Context context, Check check, Bundle extras) {
        super();
        this.misnapApi = MisnapApi.getInstance();
        this.api = SmartCheckApi.getInstance();
        this.activity = (MisnapActivity) context;
        this.progressDialog = new DelayedProgressDialog();
        this.extras = extras;
        this.check = check;
    }

    private Maker getMatchedMaker(List<Maker> makers, String makerName){
        if (makerName.isEmpty() || makers.isEmpty())
            return null;
        for (Maker maker: makers){
            if (maker.getName().equals(makerName))
                return maker;
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show(activity.getSupportFragmentManager(), "tag");
    }
    @Override
    protected Check doInBackground(Void... params) {
        File backTiff = null;
        File frontTiff = null;

        try {

            if ((Check.getFrontOriginalImage() == null || Check.getFrontOriginalImage().length <= 0)
                && (Check.getRearOriginalImage() == null || Check.getRearOriginalImage().length <= 0)){
                check.setError("Transaction failed ");
                Log.d("SmartCheck", "image of Check is null or empty");
                return check;
            }


            Transaction transaction = misnapApi.depositCheck(check);
            Log.d("SmartCheck", "depositCheck  transaction = " + transaction.toString());
            if (transaction != null && transaction.getTransactionData() != null) {
                TransactionData transData = transaction.getTransactionData();

                int transactionId = transData.getTransactionId();
                 frontTiff = misnapApi.getCheckImage(activity, transactionId, "front");
                 backTiff = misnapApi.getCheckImage(activity, transactionId, "rear");
                Check.frontProcessedImage = Util.convertTiffToByteArray(activity, frontTiff);
                Check.rearProcessedImage = Util.convertTiffToByteArray(activity, backTiff);

                Check.base64FrontProcessedImage = Util.convertTiffToBase64String(frontTiff);
                Check.base64RearProcessedImage = Util.convertTiffToBase64String(backTiff);

                int amount = transData.getAmount();
                check.setAmount(amount);
                //get micr
                if (transData.getMicr() != null && !transData.getMicr().isEmpty()){
                    Micr micr = api.parseMirc(transData.getMicr());
                    micr.setAmount(amount + "");

                    //get check ocr
                    CheckOcrRequest checkOcrRequest = new CheckOcrRequest();
                    checkOcrRequest.setFrontImage(Util.convertTiffToBase64String(frontTiff));
                    checkOcrRequest.setBackImage(Util.convertTiffToBase64String(backTiff));

                    checkOcrRequest.setAbaNumber(micr.getTransitNumber() != null ? micr.getTransitNumber(): "");

                    checkOcrRequest.setAccountNumber(micr.getAccountNumber() != null ? micr.getAccountNumber(): "");
                    if (!micr.getAccountNumber().equals(" ") ){
                        check.setExistAccountNumber(true);
                    }else {
                        check.setExistAccountNumber(false);
                    }

                    checkOcrRequest.setAmount(micr.getAmount() != null ? Integer.parseInt(micr.getAmount()):0);
                    checkOcrRequest.setCheckNumber(micr.getCheckNumber() != null ? micr.getCheckNumber():"");

                    CheckOcrResponse checkOcrResponse  = api.ocrCheck(checkOcrRequest);
                    Log.i("SmartCheck", checkOcrResponse.toString());

                    String ocrMakerName ="";
                    if (checkOcrResponse != null && checkOcrResponse.maker_name1 != null
                        && !checkOcrResponse.maker_name1.isEmpty()){
                        ocrMakerName = checkOcrResponse.maker_name1;
                    }

                    List<Maker> listMakers= api.getListMarkers(micr.accountNumber, micr.transitNumber);
                    Maker matchedMaker = getMatchedMaker(listMakers, ocrMakerName);
                    if (matchedMaker != null){
                        micr.setCheckType( matchedMaker.getKind());
                        check.setKind(matchedMaker.getKind());
                    }
                    else {
                        micr.setCheckType( checkOcrResponse.getCheck_type());
                        check.setKind(checkOcrResponse.getCheck_type());
                    }

                    check.setCheckDate(checkOcrResponse.getCheck_date());
                    check.setPayeeName(checkOcrResponse.getPayeeName());
                    check.setPayerAddress(checkOcrResponse.getAddress());
                    if (checkOcrResponse.getMaker_name1() == null || checkOcrResponse.getMaker_name1().isEmpty()){
                        if (listMakers !=null && !listMakers.isEmpty()){
                            check.setPayerName(listMakers.get(0).name);
                            if (check.getKind().equalsIgnoreCase("Other") || check.getKind().isEmpty())
                                check.setKind(listMakers.get(0).getKind());
                        }else {
                            check.setPayerName("");
                        }
                    }else {
                        check.setPayerName(checkOcrResponse.getMaker_name1());
                    }

                    check.setMicr(micr);
                    check.setRawMicr(micr.getRawMicr());
                    check.setStatus("success");
                    Check.setFrontOriginalImage(Util.compressCheckImage(Check.getFrontOriginalImage()));
                    Check.setRearOriginalImage(Util.compressCheckImage(Check.getRearOriginalImage()));

                    if (checkOcrResponse.usability.equals("Failed") && Arrays.toString(checkOcrResponse.usability_fail_reasons).contains("Payee endorsement is missing or illegible.")){
                        check.setError("Payee endorsement is missing or illegible.");
                    }

                }

                else {
                    check.setError("Transaction failed");
                    Log.d("SmartCheck", "transData.getMicr() = " + transData.getMicr());
                }
            }
            else {
                check.setError("Transaction failed");
                Log.e("SmartCheck", "transaction fail = " + transaction.toString());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            check.setError("Transaction failed");
        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("SmartCheck", e.getMessage());
            int warnings = extras.getInt(MiSnapAPI.RESULT_WARNINGS);
            if (warnings != 0) {

                String message = "WARNINGS:";
                if ((warnings & OnFrameProcessedEvent.FRAME_WRONG_DOCUMENT_CHECK) != 0) {
                    message += "\nWrong document detected";
                }
                check.setError(message);
            }
        }
        finally {
            if (frontTiff != null)
                Util.deleteImageFile(frontTiff.getAbsolutePath());
            if (backTiff != null)
                Util.deleteImageFile(backTiff.getAbsolutePath());

        }

        return check;
    }

    @Override
    protected void onPostExecute(Check check) {
        super.onPostExecute(check);
        progressDialog.dismiss();
        Intent intent = new Intent();
        intent.putExtra(SmartCheckActivity.CHECK, Parcels.wrap(check));
        Log.i("SmartCheck", "send results" + check.toString());
        progressDialog.cancel();
        activity.setResult(activity.RESULT_OK, intent);
        activity.finish();
    }
}