package com.example.nulis.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ModelStory.class, ModelChapter.class, ModelQuicky.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

        public abstract DaoStory storyDao();
        public abstract DaoChapter chapterDao();
        public abstract DaoQuicky QuickyDao();

}
