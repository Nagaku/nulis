package com.example.nulis.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = ModelStory.class,
        parentColumns = "noStory",
        childColumns = "noStory",
        onDelete = ForeignKey.CASCADE))
public class ModelChapter {

    @PrimaryKey
    private int noChapter;
    @ColumnInfo(name = "noChapPerStory")
    private int noChapPerStory;
    @ColumnInfo(name = "noStory")
    private int noStory;
    @ColumnInfo(name ="judul")
    private String judul;
    @ColumnInfo(name = "isi")
    private String isi;

    public ModelChapter(int noChapter, int noChapPerStory, int noStory, String judul, String isi) {
        this.noStory = noStory;
        this.noChapPerStory = noChapPerStory;
        this.noChapter = noChapter;
        this.judul = judul;
        this.isi = isi;
    }

    public int getNoChapter() {
        return noChapter;
    }

    public void setNoChapter(int noChapter) {
        this.noChapter = noChapter;
    }

    public int getNoChapPerStory() {
        return noChapPerStory;
    }

    public void setNoChapPerStory(int noChapPerStory) {
        this.noChapPerStory = noChapPerStory;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public int getNoStory() {
        return noStory;
    }

    public void setNoStory(int noStory) {
        this.noStory = noStory;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
