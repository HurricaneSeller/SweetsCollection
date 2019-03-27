package com.example.unpigeon.record;

import android.Manifest;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unpigeon.R;
import com.example.unpigeon.repository.local.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;
import com.shuyu.waveview.AudioWaveView;

import java.io.IOException;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.example.unpigeon.record.RecordPresenter.dip2px;

@RuntimePermissions
public class RecordActivity extends AppCompatActivity implements View.OnClickListener, RecordContract.View {
    private Button mControlButton;
    private String TAG = "moanbigking";
    private RecordPieceEntity mRecordPieceEntity;
    private TextView mContentView;
    private TextView mTimeView;
    private TextView mTitleView;
    private RecordPresenter mRecordPresenter;
    private AudioWaveView mAudioWaveView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        init();
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
        mRecordPieceEntity = (RecordPieceEntity) getIntent().getSerializableExtra(Constant.CHOSEN_TASK);
        mRecordPresenter = new RecordPresenter(this, mRecordPieceEntity);
        mRecordPresenter.setData();
        mAudioWaveView = new AudioWaveView(this, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_record_control:
                checkPermissions();
                // TODO: 3/27/19 finish this
                break;
        }
    }


    void checkPermissions() {
        askStoragePermission();
        askAudioPermission();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void askStoragePermission() {
    }

    @NeedsPermission(Manifest.permission.RECORD_AUDIO)
    void askAudioPermission() {
        try {
            mRecordPresenter.startRecord(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void showWhyNeedsStoragePermission(final PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_OPEN_STORAGE_PERMISSION)
                .setPositiveButton(Constant.ON_CLICK_KNOWN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).create().show();
    }

    @Override
    @OnShowRationale(Manifest.permission.RECORD_AUDIO)
    public void showWhyNeedsAudioPermission(final PermissionRequest request) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(Constant.ON_OPEN_AUDIO_PERMISSION)
                .setPositiveButton(Constant.ON_CLICK_KNOWN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).create().show();
    }


    @Override
    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onStoragePermissionDenied() {
        Toast.makeText(this, Constant.NOT_ENOUGH_PERMISSION_GIVEN, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    @OnPermissionDenied(Manifest.permission.RECORD_AUDIO)
    public void onAudioPermissionDenied() {
        Toast.makeText(this, Constant.NOT_ENOUGH_PERMISSION_GIVEN, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void onClickStorageOnAskAgain() {
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
    @OnNeverAskAgain(Manifest.permission.RECORD_AUDIO)
    public void onClickAudioOnAskAgain() {
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

    @Override
    public void popAlertDialog() {
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

    @Override
    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setView(byte[] data) {
//        mVisualizerFFTView.updateVisualizer(data);
    }

    @Override
    public void startView() {
        mAudioWaveView.startView();
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(4);
        mAudioWaveView.setLinePaint(paint);
        int offset = dip2px(this, 1);
        mAudioWaveView.setOffset(offset);
    }

    @Override
    public void stopView() {
        mAudioWaveView.stopView();
    }

    @Override
    public ArrayList<Short> getDataList() {
        return mAudioWaveView.getRecList();
    }


    @Override
    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    @Override
    public void setContent(String content) {
        mContentView.setText(content);
    }
}
