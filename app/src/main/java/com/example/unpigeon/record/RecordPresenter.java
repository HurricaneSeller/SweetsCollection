package com.example.unpigeon.record;

import android.content.Context;

import com.example.unpigeon.loader.downloader.AudioRecorder;
import com.example.unpigeon.loader.task.Task;
import com.example.unpigeon.repository.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

class RecordPresenter implements RecordContract.Presenter{
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
        mAudioRecorder.startRecord(null);
        mView.toast(Constant.START_RECORD);
    }

    @Override
    public void stopRecord() {
        mAudioRecorder.stopRecord();
        mView.popAlertDialog();
    }

    @Override
    public void createUploadTask() {
        Task task = new Task(mRecordPieceEntity);

    }


}
