package com.alltrustnetworks.smartcheck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alltrustnetworks.smartcheck.models.CheckType;
import com.alltrustnetworks.smartcheck.models.DocId;
import com.alltrustnetworks.smartcheck.util.Util;
import com.regula.documentreader.api.DocumentReader;
import com.regula.documentreader.api.enums.DocReaderAction;
import com.regula.documentreader.api.enums.DocReaderFrame;
import com.regula.documentreader.api.enums.LCID;
import com.regula.documentreader.api.enums.eGraphicFieldType;
import com.regula.documentreader.api.enums.eRPRM_ResultType;
import com.regula.documentreader.api.enums.eVisualFieldType;
import com.regula.documentreader.api.results.DocumentReaderDocumentType;
import com.regula.documentreader.api.results.DocumentReaderResults;
import com.regula.documentreader.api.results.DocumentReaderTextField;

import org.parceler.Parcels;

import java.io.ByteArrayOutputStream;

public class RegulaActivity extends AppCompatActivity {
    private DocId dl;
    private boolean firstPage = true;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regula);
        mPlayer = MediaPlayer.create(this, R.raw.pictureshutter);
        mPlayer.setOnCompletionListener(mediaPlayer -> {
            mediaPlayer.stop();
            mediaPlayer.release();

        });
        if (savedInstanceState == null) {
            dl = new DocId();
            if (DocumentReader.Instance().getDocumentReaderIsReady()) {
                try {
                    DocumentReader.Instance().showScanner(onFrontComplete);
                    firstPage = true;
                    updateStatus();
                }catch (Exception e) {
                    Log.e("SmartCheck", "exception 2");
                    e.printStackTrace();
                    Toast.makeText(RegulaActivity.this, "Scanning was error", Toast.LENGTH_LONG).show();
                    DocumentReader.Instance().stopScanner();
                    setResult(RESULT_CANCELED, new Intent());
                    finish();
                }
            }
            else{
                reInitDocumentReader();
            }
        }

    }

    private void reInitDocumentReader() {
        RegulaApi.getInstance().initDocumentReader(this, () -> {
            try{
                DocumentReader.Instance().showScanner(onFrontComplete);
                firstPage = true;
                updateStatus();
            }catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(RegulaActivity.this, "Scanning was error", Toast.LENGTH_LONG).show();
                DocumentReader.Instance().stopScanner();
                setResult(RESULT_CANCELED, new Intent());
                finish();
            }

        });
    }

    //DocumentReader processing callback
    private static final String TAG = "RegularActivity";


    private DocumentReader.DocumentReaderCompletion onFrontComplete = new DocumentReader.DocumentReaderCompletion() {
        @Override
        public void onCompleted(int action, DocumentReaderResults results, String error) {

            try {
                //processing is finished, all results are ready
                if (action == DocReaderAction.COMPLETE) {
//                    Log.i("SmartCheck", " Scan Passport COMPLETE = " + results.documentType.name);
                    setPlayAudio();
                    processFrontResults(results);
                    Intent intent = new Intent();
                    intent.putExtra(SmartCheckActivity.DOC_ID, Parcels.wrap(dl));
                    setResult(RESULT_OK, intent);
                    finish();
                } else {

                    //something happened before all results were ready
                    if (action == DocReaderAction.CANCEL) {
                        DocumentReader.Instance().stopScanner();
                        Toast.makeText(RegulaActivity.this, "Scanning was cancelled", Toast.LENGTH_LONG).show();
                        Intent i = new Intent();
                        setResult(RESULT_CANCELED, i);
                        finish();
                    } else if (action == DocReaderAction.ERROR) {
                        DocumentReader.Instance().stopScanner();
                        Toast.makeText(RegulaActivity.this, "Error:" + error, Toast.LENGTH_LONG).show();
                        Intent i = new Intent();
                        setResult(RESULT_CANCELED, i);
                        finish();
                    } else if (action == DocReaderAction.MORE_PAGES_AVAILABLE) {
//                        Log.i("SmartCheck", " Scan Passport successfully = " + results.documentType.dType);
//                        Log.i("SmartCheck", " Scan Passport successfully = " + results.documentType.name);
                        setPlayAudio();


//                        if (results.documentType.dType == 12) { // ID 1998
//                            returnResult(results);
//                        }

//                        if (results.documentType.. == 11 || results.documentType.name.contains("Passport")) {//ePassport
//                            returnResult(results);
//                        }

                        firstPage = false;
                        updateStatus();
                    }
                    if (action == DocReaderAction.PROCESS) {
                        if (results != null) {
                            if (results.jsonResult != null && results.documentPosition != null && results.jsonResult.results.size() > 0) {
                                DocumentReader.Instance().customization().setStatus("Your ID is being scanned. \nPlease hold it within the frame");
                            } else {
                                updateStatus();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Log.e("SmartCheck", "exception 1");
                e.printStackTrace();
                Toast.makeText(RegulaActivity.this, "Scanning was error", Toast.LENGTH_LONG).show();
                DocumentReader.Instance().stopScanner();
                RegulaActivity.this.setResult(RESULT_CANCELED, new Intent());
                RegulaActivity.this.finish();
            }
        }
    };

    private void returnResult(DocumentReaderResults results) {
        DocumentReader.Instance().stopScanner();
        processFrontResults(results);
        Intent intent = new Intent();
        intent.putExtra(SmartCheckActivity.DOC_ID, Parcels.wrap(dl));
        setResult(RESULT_OK, intent);
        finish();
    }

    private void updateStatus() {
        if (firstPage) {
            DocumentReader.Instance().customization().setStatus("Please scan the front side of your ID.\nMake sure it is inside the white frame");
        } else {
            DocumentReader.Instance().customization().setStatus("Please scan the back side of your ID.\nMake sure it is inside the white frame");
        }
    }

    private AlertDialog showDialog(String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RegulaActivity.this);
        View dialogView = getLayoutInflater().inflate(R.layout.simple_dialog, null);
        dialog.setTitle(msg);
        dialog.setView(dialogView);
        dialog.setCancelable(false);
        return dialog.show();
    }


    //show received results on the UI
    private void processFrontResults(DocumentReaderResults results) {
        if (results != null) {
            Log.i("SmartCheck", "found results");
            try {

                String middleName = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_MIDDLE_NAME), " ");
                dl.setMiddleName(middleName);

                String firstName = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_GIVEN_NAMES), " ");
                dl.setFirstName(firstName);

                String lastName = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_SURNAME), " ");
                dl.setLastName(lastName);

                String address = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_STREET), " ");
                dl.setAddress(address);

                String city = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_CITY), " ");
                dl.setCity(city);

                String zipCode = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_POSTAL_CODE), " ");
                try{
                    //sometimes zipcode return "...;;"
                    if (zipCode != null) {
                        Double.parseDouble(zipCode);
                        dl.setZipcode(zipCode);
                    }
                }catch (NumberFormatException e){
                    dl.setZipcode("");
                }

                String state = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_JURISDICTION_CODE), " ");
                dl.setState(state.toUpperCase());

//                String birthDate = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_BIRTH));
                String birthDate = results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_BIRTH);
                dl.setBirthDate(birthDate);

                String placeOfBirth = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_PLACE_OF_BIRTH), " ");
                dl.setPlaceOfBirth(placeOfBirth);

                String issuer = results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_JURISDICTION_CODE);
                dl.setIssuer(issuer);

                String IdNumber = results.getTextFieldValueByType(eVisualFieldType.FT_DOCUMENT_NUMBER);
                dl.setIdNumber(IdNumber);

                String idPassport = results.getTextFieldValueByType(eVisualFieldType.FT_PASSPORT_NUMBER);
                if (idPassport != null && !idPassport.isEmpty()) {
                    dl.setIdNumber(idPassport);
                }

//                String issueDate = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_ISSUE));
                String issueDate = results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_ISSUE);
                dl.setIssueDate(issueDate);

//                String expirationDate = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_EXPIRY));
                String expirationDate = results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_EXPIRY);
                dl.setExpirationDate(expirationDate);

                String nationality = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_NATIONALITY), " ");
                dl.setNationality(nationality);

                String authority = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_AUTHORITY), " ");
                dl.setAuthority(authority);

                String gender = results.getTextFieldValueByType(eVisualFieldType.FT_SEX);
                if (gender != null) {
                    switch (gender) {
                        case "M":
                            dl.setGender("Male");
                            break;
                        case "F":
                            dl.setGender("Female");
                            break;
                        default:
                            dl.setGender("Unknown");
                            break;
                    }

                }
                else{
                    dl.setGender("Unknown");
                }
                if(results.textResult != null && results.textResult.fields != null) {
                    for (DocumentReaderTextField textField : results.textResult.fields) {
                        String value = results.getTextFieldValueByType(textField.fieldType, textField.lcid);
                        Log.d("MainActivity", "textfield = "+ textField.fieldName+ value + "\n");
                    }
                }

                // barcode

//                String firstNameBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_GIVEN_NAMES, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (firstNameBc != null && !firstNameBc.isEmpty()) {
//                    dl.setFirstName(firstNameBc);
//                }
//
//                String lastNameBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_SURNAME, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (lastNameBc != null && !lastNameBc.isEmpty()) {
//                    dl.setLastName(lastNameBc);
//                }
//
//                String addressBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_STREET, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (addressBc != null && !addressBc.isEmpty()) {
//                    dl.setAddress(addressBc);
//                }
//
//                String cityBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_CITY, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (cityBc != null && !cityBc.isEmpty()) {
//                    dl.setCity(cityBc);
//                }
//
//                String zipCodeBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_POSTAL_CODE, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (zipCodeBc != null && !zipCodeBc.isEmpty()) {
//                    try{
//                        Double.parseDouble(zipCodeBc);
//                        dl.setZipcode(zipCodeBc);
//                    }catch (NumberFormatException e){
//                        dl.setZipcode("");
//                    }
//
//                }
//
//                String stateBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_JURISDICTION_CODE, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (stateBc != null && !stateBc.isEmpty()) {
//                    dl.setState(stateBc.toUpperCase());
//                }
//
//                String birthDateBc = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_BIRTH, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA));
//                if (birthDateBc != null && !birthDateBc.isEmpty()) {
//                    dl.setBirthDate(birthDateBc);
//                }
//
//                String placeOfBirthBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_PLACE_OF_BIRTH, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (placeOfBirthBc != null && !placeOfBirthBc.isEmpty()) {
//                    dl.setPlaceOfBirth(placeOfBirthBc);
//                }
//
//                String issuerBc = results.getTextFieldValueByType(eVisualFieldType.FT_ADDRESS_JURISDICTION_CODE, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA);
//                if (issuerBc != null && !issuerBc.isEmpty()) {
//                    dl.setIssuer(issuerBc);
//                }
//
//                String idNumberBc = results.getTextFieldValueByType(eVisualFieldType.FT_DOCUMENT_NUMBER, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA);
//                if (idNumberBc != null && !idNumberBc.isEmpty()) {
//                    dl.setIdNumber(idNumberBc);
//                }
//
//                String idPassportBc = results.getTextFieldValueByType(eVisualFieldType.FT_PASSPORT_NUMBER, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA);
//                if (idPassportBc != null && !idPassportBc.isEmpty()) {
//                    dl.setIdNumber(idPassportBc);
//                }
//
//                String issueDateBc = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_ISSUE, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA));
//                if (issueDateBc != null && !issueDateBc.isEmpty()) {
//                    dl.setIssueDate(issueDateBc);
//                }
//
//                String expirationDateBc = Util.convertRegulaDate(results.getTextFieldValueByType(eVisualFieldType.FT_DATE_OF_EXPIRY, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA));
//                if (expirationDateBc != null && !expirationDateBc.isEmpty()) {
//                    dl.setExpirationDate(expirationDateBc);
//                }
//
//                String nationalityBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_NATIONALITY, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (nationalityBc != null && !nationalityBc.isEmpty()) {
//                    dl.setNationality(nationalityBc);
//                }
//
//                String authorityBc = Util.camelCase(results.getTextFieldValueByType(eVisualFieldType.FT_AUTHORITY, LCID.NONE, eRPRM_ResultType.RPRM_RESULT_TYPE_BAR_CODES_TEXT_DATA), " ");
//                if (authorityBc != null && !authorityBc.isEmpty()) {
//                    dl.setAuthority(authorityBc);
//                }

//                if (results.documentType != null) {
//                    if (results.documentType.dType == 12) {
//                        dl.setIdType("ID Card");
//                    } else if (results.documentType.name.toLowerCase().contains("passport")) {
//                        dl.setIssuer(results.getTextFieldValueByType(eVisualFieldType.FT_ISSUING_STATE_CODE));
//                        dl.setIdType("Passport");
//                    } else if (results.documentType.dType == 49) {
//                        dl.setIdType("Driver's License");
//                    }
////                    else if (results.documentType.dType != 49 &&
////                            (idPassport != null && idPassport.isEmpty()) || (idPassportBc != null && !idPassportBc.isEmpty())) {
////                        if (results.documentType.name.toLowerCase().contains("states")) {
////                            dl.setIdType("US Passport");
////                        } else if (results.documentType.name.toLowerCase().contains("mexico")) {
////                            dl.setIdType("Mexico Passport");
////                        } else {
////                            dl.setIdType("Passport");
////                        }
////                    }
//                    else {
//                        dl.setIdType("Other");
//                    }
//                }

                ByteArrayOutputStream stream;
                Bitmap frontImage = results.getGraphicFieldImageByType(eGraphicFieldType.GF_DOCUMENT_IMAGE);

                if (frontImage != null) {
                    stream = new ByteArrayOutputStream();
                    frontImage.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                    DocId.frontProcessedImage = stream.toByteArray();
                    frontImage.recycle();
                } else {
                    Log.i("SmartCheck", "front image not present");
                }

//                Bitmap rearImage = results.getGraphicFieldImageByType(eGraphicFieldType.GT_DOCUMENT_REAR);
//
//                if (rearImage != null) {
//                    stream = new ByteArrayOutputStream();
//                    rearImage.compress(Bitmap.CompressFormat.JPEG, 70, stream);
//                    DocId.rearProcessedImage = stream.toByteArray();
//                    rearImage.recycle();
//                } else {
//                    DocId.rearProcessedImage = null;
//                    Log.i("SmartCheck", "back image not present");
//                }

                dl.setStatus("success");

                for (DocumentReaderTextField textField : results.textResult.fields) {
                    String value = results.getTextFieldValueByType(textField.fieldType, textField.lcid);
                    Log.d("MainActivity", "field type = " + textField.fieldType + "," + "field type name= " + textField.fieldName + "," + value + "\n");
                }

            } catch (Exception e) {
                Log.i("SmartCheck", "error front");
                Log.i("SmartCheck", e.getMessage());
                dl.setStatus("error");
            }
        } else {
            Log.i("SmartCheck", "error front");
            dl.setStatus("error");
        }
    }

    public void setPlayAudio() {
        mPlayer = MediaPlayer.create(this, R.raw.pictureshutter);
        mPlayer.start();
        mPlayer.setLooping(false);
    }

}
