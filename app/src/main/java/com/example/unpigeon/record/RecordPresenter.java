package com.example.unpigeon.record;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.unpigeon.record.save.AudioRecorder;
import com.example.unpigeon.record.save.RecordStreamListener;
import com.example.unpigeon.task.Task;
import com.example.unpigeon.repository.local.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

class RecordPresenter implements RecordContract.Presenter{
    private static final String TAG = "moanbigking";
    private final RecordContract.View mView;
    private final RecordPieceEntity mRecordPieceEntity;
    private AudioRecorder mAudioRecorder;
    private String fileName;

    RecordPresenter(RecordContract.View view, RecordPieceEntity recordPieceEntity) {
        mView = view;
        mRecordPieceEntity = recordPieceEntity;
        mAudioRecorder = AudioRecorder.getInstance();
    }

    @Override
    public void setData() {
        mView.setContent(mRecordPieceEntity.getContent());
        mView.setTitle(mRecordPieceEntity.getTitle());
    }

    @Override
    public void startRecord(Context context) {
        fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        mAudioRecorder.createDefaultAudio(fileName);
        mAudioRecorder.startRecord(new RecordStreamListener() {
            @Override
            public void recordOfByte(byte[] data, int begin, int end) {
                mView.setView(data);
            }
        });
        mView.toast(Constant.START_RECORD);
    }

    @Override
    public void stopRecord() {
        mAudioRecorder.stopRecord();
        mAudioRecorder.release();
        mView.popAlertDialog();
    }

    @Override
    public void createUploadTask() {
        Task task = new Task(mRecordPieceEntity);
        // TODO: 3/24/19 add the left part
    }


}
