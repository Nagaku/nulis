package com.example.nulis.model;

import android.util.Log;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ModelStory {

    @PrimaryKey
    private int noStory;
    private String judul;
    private String penulis;

    public ModelStory(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    public ModelStory(int noStory, String judul, String penulis) {
        this.noStory = noStory;
        this.judul = judul;
        this.penulis = penulis;
    }

    public int getNoStory() {
        return noStory;
    }

    public void setNoStory(int noStory) {
        this.noStory = noStory;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public void printDebug() {
        Log.d("debug_model_story", judul + penulis);
    }
}
