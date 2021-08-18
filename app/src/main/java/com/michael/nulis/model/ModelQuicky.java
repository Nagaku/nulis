package com.michael.nulis.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ModelQuicky {
    @PrimaryKey
    int no;
    @ColumnInfo(name = "isi")
    String isi;

    public ModelQuicky(int no, String isi) {
        this.no = no;
        this.isi = isi;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
