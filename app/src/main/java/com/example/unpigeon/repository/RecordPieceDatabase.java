package com.example.unpigeon.repository;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * single-instance mode
 */
@Database(entities = {RecordPieceEntity.class}, version = 1)
public abstract class RecordPieceDatabase extends RoomDatabase {
    private static final String TAG = "moanbigking";
    private static RecordPieceDatabase INSTANCE = null;

    public abstract RecordPieceDao recordPieceDao();

    private static final Object o = new Object();

    public static RecordPieceDatabase getInstance(Context context) {
        Log.d(TAG, "getInstance: 0");
        synchronized (o) {
            if (INSTANCE == null) {
                Log.d(TAG, "getInstance: 1");
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RecordPieceDatabase.class,
                        "record_piece.db").build();
            }
        }
        return INSTANCE;
    }
}
