package com.alltrustnetworks.smartcheck;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.List;
import android.widget.Toast;

import com.alltrustnetworks.smartcheck.models.DocumentType;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

public class SmartCheckActivity extends AppCompatActivity {

    public static final String DOCUMENT_TYPE = "DOCUMENT_TYPE";

    public static final String DOC_ID = "DOC_ID";
    public static final String CHECK = "CHECK";

    public static final int REGULA_ID = 108;
    public static final int MISNAP_ID = 109;

    private int documentType;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_check);
        documentType = getIntent().getIntExtra(DOCUMENT_TYPE, DocumentType.DOC_ID);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionListener permissionlistener = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    if (savedInstanceState == null) {
                        SmartCheckActivity.this.capture();
                    }
                }

                @Override
                public void onPermissionDenied(List<String> deniedPermissions) {
                    Toast.makeText(SmartCheckActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                }
            };

            TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service. Please turn on permissions.")
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
        }
        else {
            if (savedInstanceState == null) {
                capture();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void capture() {
        if (documentType == DocumentType.CHECK) {
            captureByMisnap();
        }
        else {
            captureByRegula();
        }
    }

    private void captureByRegula() {
        Intent intent = new Intent(this, RegulaActivity.class);
        startActivityForResult(intent, REGULA_ID);
    }

    private void captureByMisnap() {
        Intent intent = new Intent(this, MisnapActivity.class);
        startActivityForResult(intent, MISNAP_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, data);
        }

        this.finish();
    }
}
