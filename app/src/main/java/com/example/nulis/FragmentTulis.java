package com.example.nulis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.nulis.adapter.MainRecycleAdapter;
import com.example.nulis.model.ModelStory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTulis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTulis extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recycleView;
    private MainRecycleAdapter recycleAdapter;
    private List<ModelStory> stories = new ArrayList<ModelStory>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentTulis() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTulis.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTulis newInstance(String param1, String param2) {
        FragmentTulis fragment = new FragmentTulis();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tulis, container, false);
        populateStories();
        recycleView = view.findViewById(R.id.main_rv_1);
        recycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycleAdapter = new MainRecycleAdapter(stories);
        recycleAdapter.setListener(new MainRecycleAdapter.OnCallbackListener(){
            @Override
            public void onClick(ModelStory story) {
                MainActivity.getFm().beginTransaction().hide(MainActivity.getFragmentActive()).show(MainActivity.getFragment4()).commit();
                MainActivity.setFragmentActive(MainActivity.fragment4);
                MainActivity.setNo(story.getNoStory());
            }
        });
        recycleView.setAdapter(recycleAdapter);
        return view;
    }



    protected void populateStories() {
        ModelStory ms = new ModelStory(1, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
        ms = new ModelStory(2, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
        ms = new ModelStory(3, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
        ms = new ModelStory(4, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
        ms = new ModelStory(5, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
        ms = new ModelStory(6, "Ikrar dan Rezky", "Rycho");
        this.stories.add(ms);
    }
}