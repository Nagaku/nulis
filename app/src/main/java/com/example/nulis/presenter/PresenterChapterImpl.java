package com.example.nulis.presenter;

import com.example.nulis.model.ModelChapter;
import com.example.nulis.model.ModelStory;
import com.example.nulis.view.ViewChapter;
import com.example.nulis.view.ViewStory;

import java.util.ArrayList;
import java.util.List;

public class PresenterChapterImpl implements PresenterChapter {
    private List<ModelChapter> chapters = new ArrayList<ModelChapter>();
    private ViewChapter viewChapter;
    private int no = 0;

    public PresenterChapterImpl(ViewChapter viewChapter) {
        this.viewChapter = viewChapter;
        viewChapter.onLoad(chapters);
    }

    @Override
    public void save(ModelChapter chapter) {
        no++;
        chapter.setNoChapter(no);
        chapters.add(chapter);
        viewChapter.onSave();
    }

    @Override
    public void delete(ModelChapter chapter) {

    }

    @Override
    public void update(ModelChapter chapter) {

    }

    @Override
    public void load() {
        viewChapter.onLoad(chapters);
    }
}
