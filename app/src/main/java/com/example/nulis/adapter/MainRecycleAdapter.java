package com.example.nulis.adapter;

import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.ViewHolder> {

    private List<ModelStory> stories;
    private OnCallbackListener listener;
    private OnLongCallbackListener listener_long;
    private static List<View> hold_view = new ArrayList<View>();
    private static List<ModelStory> hold_id = new ArrayList<ModelStory>();

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
            unselect();
            hold_view = new ArrayList<View>();
            MainActivity.setIs_on_long(0);
            MainActivity.toogle_button();
            if (listener != null) {
                listener.onClick(stories.get(getBindingAdapterPosition()));
            }
        }

        @Override
        public boolean onLongClick(View view) {
            view.findViewById(R.id.recycle_view_base).setBackground(ContextCompat.getDrawable(MainActivity.getContextOfApplication(), R.drawable.recycle_long));
            MainActivity.setIs_on_long(MainActivity.getIs_on_long() + 1);
            MainActivity.toogle_button();
            hold_view.add(view);
            hold_id.add(stories.get(getBindingAdapterPosition()));
            if ((listener_long != null) && MainActivity.getIs_on_long() == 1) {
                listener_long.onClick(hold_id);
            }
            MainActivity.getButton_back().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unselect();
                    hold_view = new ArrayList<View>();
                    hold_id = new ArrayList<ModelStory>();
                    MainActivity.setIs_on_long(0);
                    MainActivity.toogle_button();
                }
            });
            return true;
        }
    }

    public static void unselect() {
        for (View temp_view: MainRecycleAdapter.hold_view) {
            temp_view.findViewById(R.id.recycle_view_base).setBackground(ContextCompat.getDrawable(MainActivity.getContextOfApplication(), R.drawable.assets_recycle));
        }
    }

    public interface OnCallbackListener {
        void onClick(ModelStory story);
    }

    public interface OnLongCallbackListener {
        void onClick(List<ModelStory> story);
    }

    public void setListener(OnCallbackListener listener) {
        this.listener = listener;
    }

    public void setListener_long(OnLongCallbackListener listener) {
        this.listener_long = listener;
    }
}
