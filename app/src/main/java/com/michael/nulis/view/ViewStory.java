package com.michael.nulis.view;

import com.michael.nulis.model.ModelStory;

import java.util.List;

public interface ViewStory {
    void onLoad(List<ModelStory> stories);
    void onSave();
    void onDelete();
    void onUpdate();
}
