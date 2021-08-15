package com.example.nulis.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DaoChapter {

    @Insert
    public void addChapter(ModelChapter chapter);

    @Update
    public void updateChapter(ModelChapter chapter);

    @Delete
    public void deleteChapter(ModelChapter chapter);

    @Query("SELECT * FROM ModelChapter where ModelChapter.noStory=:no_Story")
    List<ModelChapter> loadAllChapter(final int no_Story);

    @Query("SELECT * FROM ModelChapter where ModelChapter.noChapter=:noChapter LIMIT 1")
    ModelChapter loadOneChapter(final int noChapter);

    @Query("SELECT ModelChapter.noChapter FROM ModelChapter ORDER BY noChapter DESC LIMIT 1")
    public int getLastNumber();

    @Query("SELECT ModelChapter.noChapPerStory FROM ModelChapter where ModelChapter.noStory=:no_Story ORDER BY noChapter DESC LIMIT 1 ")
    public int getLastChapter(final int no_Story);
}
