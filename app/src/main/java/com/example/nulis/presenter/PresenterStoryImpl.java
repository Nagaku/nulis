package com.example.nulis.presenter;

import com.example.nulis.MainActivity;
import com.example.nulis.model.ModelStory;
import com.example.nulis.view.ViewStory;

import java.util.ArrayList;
import java.util.List;

public class PresenterStoryImpl implements PresenterStory{

    private List<ModelStory> stories = new ArrayList<ModelStory>();
    private ViewStory viewStory;
    private int no = 0;

    public PresenterStoryImpl(ViewStory viewStory) {
        this.viewStory = viewStory;
        stories = MainActivity.db.storyDao().loadAllStory();
        viewStory.onLoad(stories);
    }

    @Override
    public void save(ModelStory story) {
        no++;
        story.setNoStory(no);
        stories.add(story);
        viewStory.onSave();
        MainActivity.db.storyDao().addStory(story);
    }

    @Override
    public void delete(ModelStory story) {
        stories.remove(story);
        viewStory.onDelete();
        MainActivity.db.storyDao().deleteStory(story);
    }

    @Override
    public void update(ModelStory story) {
        for (ModelStory perstory: stories) {
            if (perstory.getNoStory() == story.getNoStory()) {
                perstory.setJudul(story.getJudul());
            }
        }
        viewStory.onUpdate();
        MainActivity.db.storyDao().updateStory(story);
    }

    @Override
    public void load() {
        viewStory.onLoad(stories);
    }

    protected void populateStories() {
        ModelStory ms = new ModelStory(no, "Tinker The Science Machine", "Rycho");
        this.stories.add(ms);
        no++;
        ms = new ModelStory(no, "Dwarf And The Conquerer", "Rycho");
        this.stories.add(ms);
        no++;
        ms = new ModelStory(no, "Axe The Bloodseeker", "Rycho");
        this.stories.add(ms);
        no++;
        ms = new ModelStory(no, "The Ancient One", "Rycho");
        this.stories.add(ms);
        no++;
        ms = new ModelStory(no, "Bottle With No Cap", "Rycho");
        this.stories.add(ms);
        no++;
        ms = new ModelStory(no, "Golden River", "Rycho");
        this.stories.add(ms);
        no++;
    }
}
