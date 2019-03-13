package com.example.unpigeon.record;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.VoicePieceTask;
import com.example.unpigeon.main.MainActivity;
import com.example.unpigeon.utils.PermissionsUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
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
    private VoicePieceTask voicePieceTask;
    private TextView mtextView;
    private Chronometer mchronometer;

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
        mtextView = findViewById(R.id.activity_record_text);
        voicePieceTask = (VoicePieceTask)getIntent().getSerializableExtra("TaskInformation");
        mtextView.setText(voicePieceTask.getContent());
        mchronometer = findViewById(R.id.chronometer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_record_control:
                if (!isRecording) {
                    startRecord();
                    mchronometer.start();
                } else {
                    stopRecord();
                    mchronometer.stop();
                    popAlertDialog();

                }
                break;
        }
    }

    private void startRecord() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), voicePieceTask.getContent() + "reverseme.pcm");
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

    private void popAlertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(RecordActivity.this);
        dialog.setTitle("是否确认上传");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                voicePieceTask.setFinished(true);
                Intent intent = new Intent(RecordActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }
}
