package com.example.unpigeon.repository.user;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE = null;

    public abstract UserDao UserDao();

    private static final Object o = new Object();

    public static UserDatabase getInstance(Context context) {
        synchronized (o) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class,
                        "user.db").build();
            }
        }
        return INSTANCE;
    }
}
