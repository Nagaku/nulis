package com.example.nulis.presenter;

import com.example.nulis.model.ModelChapter;

public interface PresenterChapter {
    void save(ModelChapter story);
    void delete(ModelChapter story);
    void update(ModelChapter story);
    void load();
}
