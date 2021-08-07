package com.example.nulis.model;

public class ModelChapter {

    private int noStory;
    private int noChapter;
    private String Judul;
    private String isi;

    public ModelChapter(int noStory, int noChapter, String judul) {
        this.noStory = noStory;
        this.noChapter = noChapter;
        this.Judul = judul;
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

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
