package com.example.unpigeon.record;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.utils.PermissionsUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

// TODO: 2/23/19 the recoding activity
/*
i add a baidu library
if u have a better library just change it
how to use :http://ai.baidu.com/docs#/ASR-Android-SDK/top
 */
public class RecordActivity extends AppCompatActivity implements View.OnClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback {
    private Button mControlButton;
    private String title = "sample";
    private PermissionsUtil permissionsUtil;
    private MediaRecorderTask mMediaRecorderTask;
    private boolean isRecording = false;
    private RhythmView mRhythmView;
    private String TAG = "moanbigking";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        hideActionbar();
        init();
        checkPermissions();
    }

    private void hideActionbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void init() {
        mControlButton = findViewById(R.id.activity_record_control);
        mControlButton.setOnClickListener(this);
        mRhythmView = new RhythmView(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_record_control:
                if (!isRecording) {
                    startRecord();
                } else {
                    stopRecord();
                }
                break;
        }
    }

    private void startRecord() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), title + "reverseme.pcm");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mMediaRecorderTask = new MediaRecorderTask();
        mMediaRecorderTask.setOnVolumeChangeListener(new MediaRecorderTask.OnVolumeChangeListener() {
            @Override
            public void volumeChange(float per) {
                mRhythmView.setPerHeight(per);
                Log.d(TAG, "volumeChange: ");
            }
        });
        mMediaRecorderTask.start(file);
        isRecording = true;
    }

    private void checkPermissions() {
        permissionsUtil = new PermissionsUtil(this);
        String[] permissions = new String[]{Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        permissionsUtil.shouldShowPermissionRationale(permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        permissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void stopRecord() {
        mMediaRecorderTask.stop();
        isRecording = false;
    }
}
