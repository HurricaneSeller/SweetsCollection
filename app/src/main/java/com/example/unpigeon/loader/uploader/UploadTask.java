package com.example.unpigeon.loader.uploader;

import com.example.unpigeon.loader.task.Task;
import com.example.unpigeon.repository.RecordPieceEntity;

import androidx.annotation.NonNull;

public class UploadTask extends Task {
    public UploadTask(@NonNull RecordPieceEntity recordPieceEntity) {
        super(recordPieceEntity);
    }
}
