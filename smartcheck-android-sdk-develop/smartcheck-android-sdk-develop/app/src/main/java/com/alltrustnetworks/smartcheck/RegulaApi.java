package com.alltrustnetworks.smartcheck;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.regula.documentreader.api.DocumentReader;
import com.regula.documentreader.api.enums.DocReaderOrientation;

import java.io.InputStream;

public class RegulaApi {

    public interface DocumentReaderInitSuccessCallback{
        void successCallback();
    }
    private static final RegulaApi ourInstance = new RegulaApi();
    public static RegulaApi getInstance() {
        return ourInstance;
    }

    private RegulaApi() {}
    public void initDocumentReader(Context context, DocumentReaderInitSuccessCallback callback){
        try {
            InputStream licInput = context.getResources().openRawResource(R.raw.regula);
            int available = licInput.available();
            byte[] license = new byte[available];
            //noinspection ResultOfMethodCallIgnored
            licInput.read(license);
            //Initializing the reader
            DocumentReader.Instance().initializeReader(context, license, new DocumentReader.DocumentReaderInitCompletion() {
                @Override
                public void onInitCompleted(boolean success, String error) {
//                    DocumentReader.Instance().customization().setShowHelpAnimation(true);
//                    DocumentReader.Instance().customization().setShowStatusMessages(true);
//                    DocumentReader.Instance().customization().setStatusTextSize(18);
//                    DocumentReader.Instance().customization().setShowResultStatusMessages(true);
//                    DocumentReader.Instance().functionality().setOrientation(DocReaderOrientation.PORTRAIT);
//                    DocumentReader.Instance().processParams().multipageProcessing = true;
//                    DocumentReader.Instance().processParams().dateFormat = "yyyy-mm-dd";
//                    DocumentReader.Instance().processParams().scenario = "FullProcess";
//                    DocumentReader.Instance().functionality().setShowSkipNextPageButton(true);
//                    DocumentReader.Instance().functionality().setShowCaptureButton(true);
//                    DocumentReader.Instance().processParams.debugSaveImages = true;
//                    DocumentReader.Instance().processParams.debugSaveLogs = true;
//                    DocumentReader.Instance().processParams.logs= true;

                    //initialization successful
                    if (success) {
                        callback.successCallback();
                    }
                    else {
                        Toast.makeText(context, "Init failed:" + error, Toast.LENGTH_LONG).show();
                    }
                }
            });

            licInput.close();

        } catch (Exception ex) {
            Log.e("SmartCheck", ex.getMessage());
            ex.printStackTrace();
        }
    }

}
