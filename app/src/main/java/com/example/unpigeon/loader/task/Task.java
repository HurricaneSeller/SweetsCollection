package com.example.unpigeon.loader.task;

import com.example.unpigeon.repository.RecordPieceEntity;

public class Task {
    private boolean isRecordFinished;
    private boolean isUploadFished;
    private final RecordPieceEntity mRecordPieceEntity;

    public Task(RecordPieceEntity recordPieceEntity) {
        mRecordPieceEntity = recordPieceEntity;
    }

}
