package com.example.nulis.model;

public class ModelStory {

    private int noStory;
    private String judul;
    private String penulis;

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
}
