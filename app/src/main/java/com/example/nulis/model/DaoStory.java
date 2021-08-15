package com.example.nulis.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DaoStory {

    @Insert
    public void addStory(ModelStory story);

    @Update
    public void updateStory(ModelStory story);

    @Delete
    public void deleteStory(ModelStory story);

    @Query("SELECT * FROM ModelStory")
    List<ModelStory> loadAllStory();

}
