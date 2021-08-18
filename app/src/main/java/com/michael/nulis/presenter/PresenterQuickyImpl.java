package com.michael.nulis.presenter;

import com.michael.nulis.MainActivity;
import com.michael.nulis.model.ModelQuicky;
import com.michael.nulis.view.ViewQuicky;

public class PresenterQuickyImpl implements PresenterQuicky{
    ModelQuicky mq;
    ViewQuicky vq;
    int no = 1;

    public PresenterQuickyImpl(ViewQuicky vq) {
        this.vq = vq;
        this.mq = MainActivity.db.QuickyDao().loadQuicky();
        if(this.mq == null) {
            this.mq = new ModelQuicky(1, "");
            MainActivity.db.QuickyDao().addQuicky(this.mq);
        }
    }

    @Override
    public void update(ModelQuicky mq) {
        this.mq = mq;
        MainActivity.db.QuickyDao().editQuicky(this.mq);
    }

    @Override
    public void load() {
        this.mq = MainActivity.db.QuickyDao().loadQuicky();
        vq.onLoad(this.mq);
    }
}