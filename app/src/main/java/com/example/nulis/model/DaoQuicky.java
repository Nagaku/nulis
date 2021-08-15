package com.example.nulis.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoQuicky {
    @Insert
    void addQuicky(ModelQuicky mq);
    @Update
    void editQuicky(ModelQuicky mq);
    @Query("SELECT * FROM ModelQuicky")
    ModelQuicky loadQuicky();
}
