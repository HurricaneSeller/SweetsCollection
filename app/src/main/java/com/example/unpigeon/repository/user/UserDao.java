package com.example.unpigeon.repository.user;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM userentity")
    UserEntity getLocalUser();

    @Insert
    void insert(UserEntity userEntity);

}
