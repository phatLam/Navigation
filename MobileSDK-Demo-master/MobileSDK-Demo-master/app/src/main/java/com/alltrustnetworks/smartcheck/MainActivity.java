package com.alltrustnetworks.smartcheck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alltrustnetworks.smartcheck.models.Check;
import com.alltrustnetworks.smartcheck.models.DocumentType;
import com.alltrustnetworks.smartcheck.models.DocId;

import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity {
    private static final String URL = "https://atws-mobile.herokuapp.com";
    private static final String BUSINESS_ID = "business_8bf76830-35e3-4483-845a-66112c6aec97";
    private static final String LOCATION_ID = "location_2b1f5d1a-13b7-46fd-a7c4-9b6859168a2f";
    private static final String API_TOKEN = "at_key-2df20a76-11e2-4924-9565-2c74372f4e91";

    private static final String MISNAP_URL = "https://alltrustuat.rdcselect.com/externalservices";
    private static final String MISNAP_USERNAME = "checkuser";
    private static final String MISNAP_PASSWORD = "P@ych3ck20!8";

    private int currentDocumentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SmartCheckSDK.getInstance();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        Button dlBtn = (Button) findViewById(R.id.driveLicenseBtn);
        Button checkBtn = (Button) findViewById(R.id.checkBtn);

        dlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startSmartCheck(DocumentType.DOC_ID);
            }
        });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startSmartCheck(DocumentType.CHECK);
            }
        });

        drawImages();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == DocumentType.DOC_ID) {
            Check.rearOriginalImage = null;
            Check.frontOriginalImage = null;
            Check.frontProcessedImage = null;
            Check.rearProcessedImage = null;

            try {
                DocId docId = Parcels.unwrap(intent.getParcelableExtra(SmartCheckActivity.DOC_ID));

                if (docId != null) {
                    TextView textView = (TextView) findViewById(R.id.resultText);
                    textView.setText(docId.toString());
                }

                drawImages();
            }
            catch(Exception e) {

            }
        }

        if (requestCode == DocumentType.CHECK) {
            DocId.frontProcessedImage = null;
            DocId.rearProcessedImage = null;

            try {
                Check check = Parcels.unwrap(intent.getParcelableExtra(SmartCheckActivity.CHECK));
                if (check != null) {
                    TextView textView = (TextView) findViewById(R.id.resultText);
                    textView.setText(check.toString());
                }

                drawImages();
            }
            catch(Exception e) {

            }
        }
    }

    private void drawImages() {
        Bitmap bitmap;

        if (DocId.frontProcessedImage != null && DocId.frontProcessedImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(DocId.frontProcessedImage, 0, DocId.frontProcessedImage.length);
            ImageView frontProcessedImage = (ImageView) findViewById(R.id.image_front_processed);
            frontProcessedImage.setImageBitmap(bitmap);
        }

        if (DocId.rearProcessedImage != null && DocId.rearProcessedImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(DocId.rearProcessedImage, 0, DocId.rearProcessedImage.length);
            ImageView rearProcessedImage = (ImageView) findViewById(R.id.image_rear_processed);
            rearProcessedImage.setImageBitmap(bitmap);
        }

        if (Check.frontProcessedImage != null && Check.frontProcessedImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(Check.frontProcessedImage, 0, Check.frontProcessedImage.length);
            ImageView frontProcessedImage = (ImageView) findViewById(R.id.image_front_processed);
            frontProcessedImage.setImageBitmap(bitmap);
        }

        if (Check.rearProcessedImage != null && Check.rearProcessedImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(Check.rearProcessedImage, 0, Check.rearProcessedImage.length);
            ImageView rearProcessedImage = (ImageView) findViewById(R.id.image_rear_processed);
            rearProcessedImage.setImageBitmap(bitmap);
        }

        if (Check.frontOriginalImage != null && Check.frontOriginalImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(Check.frontOriginalImage, 0, Check.frontOriginalImage.length);
            ImageView frontOriginalImage = (ImageView) findViewById(R.id.image_front_original);
            frontOriginalImage.setImageBitmap(bitmap);
        }

        if (Check.rearOriginalImage != null && Check.rearOriginalImage.length > 0) {
            bitmap = BitmapFactory.decodeByteArray(Check.rearOriginalImage, 0, Check.rearOriginalImage.length);
            ImageView rearOriginalImage = (ImageView) findViewById(R.id.image_rear_original);
            rearOriginalImage.setImageBitmap(bitmap);
        }

    }

    private void startSmartCheck(int documentType) {
        currentDocumentType = documentType;
        Intent intent = new Intent(this, SmartCheckActivity.class);
        intent.putExtra(SmartCheckActivity.DOCUMENT_TYPE, documentType);
        this.startActivityForResult(intent, documentType);
    }
}
