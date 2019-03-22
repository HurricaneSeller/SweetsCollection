package com.example.unpigeon.repository.local;

import java.io.Serializable;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class RecordPieceEntity implements Serializable {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "record_content")
    private String content;

    @ColumnInfo(name = "record_title")
    private String title;

    @Ignore
    private boolean isFinished;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof RecordPieceEntity) {
            return this.getUid() == ((RecordPieceEntity)obj).getUid();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
