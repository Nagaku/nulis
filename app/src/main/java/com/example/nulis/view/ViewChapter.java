package com.example.nulis.view;

import com.example.nulis.model.ModelChapter;

import java.util.List;

public interface ViewChapter {
    void onLoad(List<ModelChapter> chapters);
    void onSave();
    void onDelete();
    void onUpdate();
}
