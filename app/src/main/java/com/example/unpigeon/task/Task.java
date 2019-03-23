package com.example.unpigeon.task;

import com.example.unpigeon.repository.local.RecordPieceEntity;

import androidx.annotation.NonNull;

public class Task {
    private boolean isFinished = false;
    private String content;
    private String title;
    private final RecordPieceEntity mRecordPieceEntity;

    public Task(@NonNull RecordPieceEntity recordPieceEntity) {
        if (recordPieceEntity.isFinished()) {
            shutdown();
        }
        mRecordPieceEntity = recordPieceEntity;
        content = recordPieceEntity.getContent();
        title = recordPieceEntity.getTitle();
    }

    public final void shutdown() {
        isFinished = true;
    }

    public final String getContent() {
        return content;
    }

    public final String getTitle() {
        return title;
    }
}
