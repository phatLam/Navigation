package com.alltrustnetworks.smartcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alltrustnetworks.smartcheck.tasks.ImageProcessor;
import com.miteksystems.misnap.misnapworkflow.MiSnapWorkflowActivity;
import com.miteksystems.misnap.misnapworkflow.params.WorkflowApi;
import com.miteksystems.misnap.params.MiSnapAPI;
import com.miteksystems.misnap.params.MiSnapApiConstants;

import org.json.JSONException;
import org.json.JSONObject;
import com.alltrustnetworks.smartcheck.models.Check;

public class MisnapActivity extends AppCompatActivity {
    private SmartCheckApi api;
    private MisnapApi misnapApi;
    private static final int CAPTURE_FRONT = 42;
    private static final int CAPTURE_BACK = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.misnap_layout);
        misnapApi = MisnapApi.getInstance();

        if (savedInstanceState == null) {
            captureFront();
        }
    }

    private void captureFront() {
        Intent intent = makeRequestIntent(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_FRONT);
        startActivityForResult(intent, CAPTURE_FRONT);
    }

    private void captureBack() {
        Intent intent = makeRequestIntent(MiSnapApiConstants.PARAMETER_DOCTYPE_CHECK_BACK);
        startActivityForResult(intent, CAPTURE_BACK);
    }

    private Intent makeRequestIntent(String checkFace) {
        Intent intentMiSnap = null;

        try {
            JSONObject misnapParams = new JSONObject();
            misnapParams.put(MiSnapAPI.MiSnapGeoRegion, MiSnapApiConstants.PARAMETER_GEO_REGION_US);
            misnapParams.put(MiSnapAPI.MiSnapDocumentType, checkFace);
            misnapParams.put(MiSnapAPI.MiSnapFlattenAndCrop, 1);
            misnapParams.put(MiSnapAPI.MiSnapTorchMode,0);
            JSONObject workflowParams = new JSONObject();
            workflowParams.put(WorkflowApi.MISNAP_WORKFLOW_TRACK_GLARE, "1");

            intentMiSnap = new Intent(this, MiSnapWorkflowActivity.class);
            intentMiSnap.putExtra(MiSnapAPI.JOB_SETTINGS, misnapParams.toString());
            intentMiSnap.putExtra(WorkflowApi.WORKFLOW_SETTINGS, workflowParams.toString());
            return intentMiSnap;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return intentMiSnap;
    }


    private void processFront(Intent data) {
        Bundle extras = data.getExtras();
        byte[] frontImage = data.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA);
        Check.frontOriginalImage = frontImage;
        captureBack();
    }

    private void processBack(Intent data) {
        Bundle extras = data.getExtras();
        Check check = new Check();
        byte[] backImage = data.getByteArrayExtra(MiSnapAPI.RESULT_PICTURE_DATA);
        Check.rearOriginalImage = backImage;
        new ImageProcessor(this, check, extras).execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Log.i("SmartCheck", "result canceled");
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
            return;
        }

        if (requestCode == CAPTURE_FRONT && RESULT_OK == resultCode) {
            Log.i("SmartCheck", "process front");
            processFront(data);
        }

        if (requestCode == CAPTURE_BACK && RESULT_OK == resultCode) {
            Log.i("SmartCheck", "process back");
            processBack(data);
        }
    }
}
