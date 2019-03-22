package com.example.unpigeon.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * single-instance mode
 */
@Database(entities = {RecordPieceEntity.class}, version = 1)
public abstract class RecordPieceDatabase extends RoomDatabase {
    private static RecordPieceDatabase INSTANCE = null;

    public abstract RecordPieceDao recordPieceDao();

    private static final Object o = new Object();

    public static RecordPieceDatabase getInstance(Context context) {
        synchronized (o) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecordPieceDatabase.class,
                        "record_piece.db").build();
            }
        }
        return INSTANCE;
    }
}
