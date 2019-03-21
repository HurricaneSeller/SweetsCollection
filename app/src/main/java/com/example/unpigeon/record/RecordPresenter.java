package com.example.unpigeon.record;

import android.content.Context;
import android.widget.Toast;

import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.example.unpigeon.loader.downloader.AudioRecorder;
import com.example.unpigeon.repository.RecordPieceEntity;
import com.example.unpigeon.utils.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;

class RecordPresenter implements RecordContract.Presenter{
    private final RecordContract.View mView;
    private final RecordPieceEntity mRecordPieceEntity;
    private AudioRecorder mAudioRecorder;

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
        String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        mAudioRecorder.createDefaultAudio(fileName);
        mAudioRecorder.startRecord(null);
        Toast.makeText(context, Constant.START_RECORD, Toast.LENGTH_SHORT).show();
        EventManager asr = EventManagerFactory.create(context, "asr");

    }

    @Override
    public void stopRecord() {
        mAudioRecorder.stopRecord();
        mView.popAlertDialog();
    }

}
