package com.example.unpigeon.record;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.loader.downloader.AudioRecorder;
import com.example.unpigeon.main.MainActivity;
import com.example.unpigeon.repository.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class RecordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mControlButton;
    //    private RhythmView mRhythmView;
    private String TAG = "moanbigking";
    private RecordPieceEntity mRecordPieceEntity;
    private TextView mContentView;
    private TextView mTimeView;
    private TextView mTitleView;
    private AudioRecorder mAudioRecorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        hideStatusBar();
        init();
    }

    private void hideStatusBar() {
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void init() {
        mControlButton = findViewById(R.id.activity_record_control);
        mControlButton.setOnClickListener(this);
        mContentView = findViewById(R.id.activity_record_content_view);
        mTitleView = findViewById(R.id.activity_record_title_view);
        mTimeView = findViewById(R.id.activity_record_time);
        mControlButton.setOnClickListener(this);
//        mRhythmView = new RhythmView(this);
        mRecordPieceEntity = (RecordPieceEntity) getIntent().getSerializableExtra(Constant.CHOSEN_TASK);
        mContentView.setText(mRecordPieceEntity.getContent());
        mAudioRecorder = AudioRecorder.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_record_control:
                if (mAudioRecorder.getStatus() == AudioRecorder.Status.STATUS_NO_READY) {
                    RecordActivityPermissionsDispatcher.askAudioPermissionWithPermissionCheck(this);
                    RecordActivityPermissionsDispatcher.askStoragePermissionWithPermissionCheck(this);
                    onClickRecord();
                } else {
                    stopRecord();
                }
                break;
        }
    }


    void onClickRecord() {
        askStoragePermission();
        askAudioPermission();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void askStoragePermission() {
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    void askAudioPermission() {
        String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        mAudioRecorder.createDefaultAudio(fileName);
        mAudioRecorder.startRecord(null);
        Toast.makeText(this, Constant.START_RECORD, Toast.LENGTH_SHORT).show();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showWhyNeedsStoragePermission(final PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_OPEN_STORAGE_PERMISSION)
                .setPositiveButton(Constant.ON_CLICK_KNOWN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).create().show();
    }

    @OnShowRationale(Manifest.permission.RECORD_AUDIO)
    void showWhyNeedsAudioPermission(final PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_OPEN_AUDIO_PERMISSION)
                .setPositiveButton(Constant.ON_CLICK_KNOWN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).create().show();
    }


    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onStoragePermissionDenied() {
        Toast.makeText(this, Constant.NOT_ENOUGH_PERMISSION_GIVEN, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnPermissionDenied(Manifest.permission.RECORD_AUDIO)
    void onAudioPermissionDenied() {
        Toast.makeText(this, Constant.NOT_ENOUGH_PERMISSION_GIVEN, Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void onClickStorageOnAskAgain() {
        // TODO: 3/21/19  fix it
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_NO_ASK_AGAIN)
                .setPositiveButton(Constant.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }

    @OnNeverAskAgain(Manifest.permission.RECORD_AUDIO)
    void onClickAudioOnAskAgain() {
        // TODO: 3/21/19  fix it
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_NO_ASK_AGAIN)
                .setPositiveButton(Constant.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RecordActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private void stopRecord() {
        mAudioRecorder.stopRecord();
        popAlertDialog();
    }

    private void popAlertDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(RecordActivity.this);
        dialog.setTitle(Constant.CHECK_COMMIT);
        dialog.setPositiveButton(Constant.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                // TODO: 3/21/19 update
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: 3/21/19 save local
            }
        });
        dialog.show();
    }
}
