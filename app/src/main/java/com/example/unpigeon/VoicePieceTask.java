package com.example.unpigeon;

import java.io.Serializable;

import androidx.annotation.Nullable;

// bean
public class VoicePieceTask implements Serializable {
    private String content;
    private boolean isFinished;

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
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
