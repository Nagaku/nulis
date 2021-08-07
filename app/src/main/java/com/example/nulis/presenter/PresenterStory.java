package com.example.nulis.presenter;

import com.example.nulis.model.ModelStory;

public interface PresenterStory {
    void save(ModelStory story);
    void delete(ModelStory story);
    void update(ModelStory story);
    void load();
}
