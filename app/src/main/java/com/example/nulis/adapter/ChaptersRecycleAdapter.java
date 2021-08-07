package com.example.nulis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nulis.R;
import com.example.nulis.model.ModelChapter;

import java.util.List;

public class ChaptersRecycleAdapter extends RecyclerView.Adapter<ChaptersRecycleAdapter.ViewHolder> {

    private List<ModelChapter> chapters;
    private OnCallbackListener listener;

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
        holder.noChapter.setText(String.valueOf(chapter.getNoChapter()));
        holder.Judul.setText(chapter.getJudul());
    }

    @Override
    public int getItemCount() {
        return chapters.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView noChapter;
        TextView Judul;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noChapter = itemView.findViewById(R.id.recycle_view_chapter_no);
            Judul = itemView.findViewById(R.id.recycle_view_chapter_judul);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(chapters.get(getBindingAdapterPosition()));
            }
        }
    }

    public interface OnCallbackListener {
        void onClick(ModelChapter chapters);
    }

    public void setListener(OnCallbackListener listener) {
        this.listener = listener;
    }
}
