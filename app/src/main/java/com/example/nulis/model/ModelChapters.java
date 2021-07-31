package com.example.nulis.model;

public class ModelChapters {

    private int noStory;
    private int noChapter;
    private String Judul;

    public ModelChapters(int noStory, int noChapter, String judul) {
        this.noStory = noStory;
        this.noChapter = noChapter;
        Judul = judul;
    }

    public int getNoStory() {
        return noStory;
    }

    public void setNoStory(int noStory) {
        this.noStory = noStory;
    }

    public int getNoChapter() {
        return noChapter;
    }

    public void setNoChapter(int noChapter) {
        this.noChapter = noChapter;
    }

    public String getJudul() {
        return Judul;
    }

    public void setJudul(String judul) {
        Judul = judul;
    }
}
