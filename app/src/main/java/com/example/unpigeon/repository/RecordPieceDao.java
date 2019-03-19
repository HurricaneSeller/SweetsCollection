package com.example.unpigeon.repository;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface RecordPieceDao {
    @Query("SELECT * FROM recordpieceentity")
    List<RecordPieceEntity> getAll();

    @Query("SELECT * FROM recordpieceentity WHERE uid LIKE :uid")
    RecordPieceEntity getById(int uid);

    @Insert
    void insert(RecordPieceEntity recordPieceEntity);

    @Delete
    void delete(RecordPieceEntity recordPieceEntity);
}