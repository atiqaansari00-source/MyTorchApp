package com.example.mytorchapp;

import android.content.Context;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnOn, btnOff;
    CameraManager cameraManager;
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkSelfPermission(android.Manifest.permission.CAMERA) != getPackageManager().PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.CAMERA}, 101);
        }

        btnOn = findViewById(R.id.btnOn);
        btnOff = findViewById(R.id.btnOff);

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cameraManager.setTorchMode(cameraId, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cameraManager.setTorchMode(cameraId, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}