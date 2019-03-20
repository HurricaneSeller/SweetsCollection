package com.example.unpigeon.record;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.unpigeon.R;
import com.example.unpigeon.main.MainActivity;
import com.example.unpigeon.repository.RecordPieceEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

//@RuntimePermissions
public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mControlButton;
    private String title = "sample";
    private MediaRecorderTask mMediaRecorderTask;
    private boolean isRecording = false;
    private RhythmView mRhythmView;
    private String TAG = "moanbigking";
    private RecordPieceEntity mRecordPieceEntity;
    private TextView mContentView;
    private TextView mTimeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        hideActionbar();
        init();
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
        mContentView = findViewById(R.id.activity_record_text);
        mRecordPieceEntity = (RecordPieceEntity) getIntent().getSerializableExtra("TaskInformation");
        mContentView.setText(mRecordPieceEntity.getContent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_record_control:
                if (!isRecording) {
                    startRecord();
                } else {
                    stopRecord();
                    popAlertDialog();
                }
                break;
        }
    }


//    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private void startRecord() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                mRecordPieceEntity.getContent() + ".pcm");
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

    private void stopRecord() {
        mMediaRecorderTask.stop();
        isRecording = false;
        // TODO: 3/20/19  
    }

    private void popAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RecordActivity.this);
        dialog.setTitle("是否确认上传");
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mRecordPieceEntity.setFinished(true);
                //AudioUploader uploader = new AudioUploader(Environment.getExternalStorageDirectory()
                // .getAbsolutePath()+"/"+mRecordPieceEntity.getContent()+".wav");
                //uploader.upLode();
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
