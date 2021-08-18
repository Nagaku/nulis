package com.michael.nulis.model;

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

    @Query("SELECT ModelStory.noStory FROM ModelStory ORDER BY noStory DESC LIMIT 1")
    public int getLastNumber();
}
