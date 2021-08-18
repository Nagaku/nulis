package com.michael.nulis.presenter;

import android.util.Log;

import com.michael.nulis.MainActivity;
import com.michael.nulis.model.ModelChapter;
import com.michael.nulis.view.ViewChapter;

import java.util.ArrayList;
import java.util.List;

public class PresenterChapterImpl implements PresenterChapter {
    private List<ModelChapter> chapters = new ArrayList<ModelChapter>();
    private ViewChapter viewChapter;
    private int no = 0;
    private int noChapter = 0;
    private int noStory = 0;

    public PresenterChapterImpl(ViewChapter viewChapter, int noStory) {
        this.viewChapter = viewChapter;
        this.noStory = noStory;
        this.no = MainActivity.db.chapterDao().getLastNumber();
        this.noChapter = MainActivity.db.chapterDao().getLastChapter(this.noStory);
        chapters = MainActivity.db.chapterDao().loadAllChapter(this.noStory);
        viewChapter.onLoad(chapters);
    }

    @Override
    public void save(ModelChapter chapter) {
        no++;
        noChapter++;
        chapter.setNoChapter(no);
        chapter.setNoChapPerStory(noChapter);
        chapters.add(chapter);
        MainActivity.db.chapterDao().addChapter(chapter);
        viewChapter.onSave();
    }

    @Override
    public void delete(ModelChapter chapter) {
        chapters.remove(chapter);
        viewChapter.onDelete();
        MainActivity.db.chapterDao().deleteChapter(chapter);
    }

    @Override
    public void update(ModelChapter chapter) {
        for (ModelChapter perchapter: chapters) {
            Log.d("edit_chap",  "per " + String.valueOf(perchapter.getNoChapPerStory()) + " == " + String.valueOf(chapter.getNoChapPerStory()));
            Log.d("edit_chap",  "per " + perchapter.getJudul() + " == " + chapter.getJudul());
            if (perchapter.getNoChapPerStory() == chapter.getNoChapPerStory()) {
                perchapter.setJudul(chapter.getJudul());
                perchapter.setIsi(chapter.getIsi());
            }
        }
        viewChapter.onUpdate();
        MainActivity.db.chapterDao().updateChapter(chapter);
    }

    @Override
    public void load() {
        viewChapter.onLoad(chapters);
    }
}
