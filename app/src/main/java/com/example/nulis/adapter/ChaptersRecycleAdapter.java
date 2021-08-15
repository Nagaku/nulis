package com.example.nulis.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nulis.MainActivity;
import com.example.nulis.R;
import com.example.nulis.model.ModelChapter;
import com.example.nulis.model.ModelStory;

import java.util.ArrayList;
import java.util.List;

public class ChaptersRecycleAdapter extends RecyclerView.Adapter<ChaptersRecycleAdapter.ViewHolder> {

    private List<ModelChapter> chapters;
    private OnCallbackListener listener;
    private OnLongCallbackListener listener_long;
    private static List<View> hold_view = new ArrayList<View>();
    private static List<ModelChapter> hold_id = new ArrayList<ModelChapter>();

    public ChaptersRecycleAdapter(List<ModelChapter> chapters) {
        this.chapters = chapters;
    }

    @NonNull
    @Override
    public ChaptersRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_chapters, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChaptersRecycleAdapter.ViewHolder holder, int position) {
        ModelChapter chapter = this.chapters.get(position);
        holder.noChapter.setText(String.valueOf(chapter.getNoChapPerStory()));
        holder.Judul.setText(chapter.getJudul());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView noChapter;
        TextView Judul;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noChapter = itemView.findViewById(R.id.recycle_view_chapter_no);
            Judul = itemView.findViewById(R.id.recycle_view_chapter_judul);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("long", "klikk dari dalam");
            unselect();
            hold_view = new ArrayList<View>();
            MainActivity.setIs_on_long(0);
            MainActivity.toogle_button();
            if (listener != null) {
                listener.onClick(chapters.get(getBindingAdapterPosition()));
            }
        }


        @Override
        public boolean onLongClick(View view) {
            Log.d("long", "dakow");
            view.findViewById(R.id.recycle_view_chapter_base).setBackground(ContextCompat.getDrawable(MainActivity.getContextOfApplication(), R.drawable.recycle_long));
            MainActivity.setIs_on_long(MainActivity.getIs_on_long() + 1);
            MainActivity.toogle_button();
            hold_view.add(view);
            hold_id.add(chapters.get(getBindingAdapterPosition()));
            if ((listener_long != null) && MainActivity.getIs_on_long() == 1) {
                listener_long.onClick(hold_id);
            }
            MainActivity.getButton_back().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unselect();
                    hold_view = new ArrayList<View>();
                    hold_id = new ArrayList<ModelChapter>();
                    MainActivity.setIs_on_long(0);
                    MainActivity.toogle_button();
                }
            });
            return true;
        }
    }

    public static void unselect() {
        for (View temp_view: ChaptersRecycleAdapter.hold_view) {
            temp_view.findViewById(R.id.recycle_view_chapter_base).setBackground(ContextCompat.getDrawable(MainActivity.getContextOfApplication(), R.drawable.assets_recycle));
        }
    }

    public interface OnCallbackListener {
        void onClick(ModelChapter chapters);
    }

    public interface OnLongCallbackListener {
        void onClick(List<ModelChapter> chapters);
    }

    public void setListener_long(OnLongCallbackListener listener) {
        this.listener_long = listener;
    }

    public void setListener(OnCallbackListener listener) {
        this.listener = listener;
    }
}
