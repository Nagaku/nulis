package com.michael.nulis.presenter;

import com.michael.nulis.model.ModelStory;

public interface PresenterStory {
    void save(ModelStory story);
    void delete(ModelStory story);
    void update(ModelStory story);
    void load();
}
