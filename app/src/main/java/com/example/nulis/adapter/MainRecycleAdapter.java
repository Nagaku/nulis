package com.example.nulis.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nulis.MainActivity;
import com.example.nulis.R;
import com.example.nulis.model.ModelStory;

import java.util.List;

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.ViewHolder> {

    private List<ModelStory> stories;
    private OnCallbackListener listener;

    public MainRecycleAdapter(List<ModelStory> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public MainRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecycleAdapter.ViewHolder holder, int position) {
        ModelStory stories = this.stories.get(position);
        holder.textViewJudul.setText(stories.getJudul());
//        holder.textViewPenulis.setText(stories.getPenulis());
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        TextView textViewJudul;
        //TextView textViewPenulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJudul = itemView.findViewById(R.id.recycle_view_judul);
            //textViewPenulis = itemView.findViewById(R.id.recycle_view_penulis);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(stories.get(getBindingAdapterPosition()));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            // Handle long click
            // Return true to indicate the click was handled

            view.findViewById(R.id.recycle_view_base).setBackground(ContextCompat.getDrawable(MainActivity.getContextOfApplication(), R.drawable.bottom));
            MainActivity.getButton_tambah().setVisibility(View.GONE);
            return true;
        }
    }

    public interface OnCallbackListener {
        void onClick(ModelStory story);
    }

    public void setListener(OnCallbackListener listener) {
        this.listener = listener;
    }
}
