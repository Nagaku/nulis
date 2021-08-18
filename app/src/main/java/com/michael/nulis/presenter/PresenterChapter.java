package com.michael.nulis.presenter;

import com.michael.nulis.model.ModelChapter;

public interface PresenterChapter {
    void save(ModelChapter story);
    void delete(ModelChapter story);
    void update(ModelChapter story);
    void load();
}
