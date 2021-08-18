package com.michael.nulis.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoQuicky {
    @Insert
    void addQuicky(ModelQuicky mq);
    @Update
    void editQuicky(ModelQuicky mq);
    @Query("SELECT * FROM ModelQuicky")
    ModelQuicky loadQuicky();
}
