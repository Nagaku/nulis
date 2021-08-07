package com.example.nulis;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nulis.adapter.ChaptersRecycleAdapter;
import com.example.nulis.adapter.MainRecycleAdapter;
import com.example.nulis.model.ModelChapter;
import com.example.nulis.model.ModelStory;
import com.example.nulis.presenter.GlobalFragment;
import com.example.nulis.presenter.PresenterChapter;
import com.example.nulis.presenter.PresenterChapterImpl;
import com.example.nulis.presenter.PresenterStory;
import com.example.nulis.presenter.PresenterStoryImpl;
import com.example.nulis.view.ViewChapter;
import com.example.nulis.view.ViewStory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChapters#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChapters extends Fragment implements ViewChapter, GlobalFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleView;
    private ChaptersRecycleAdapter recycleAdapter;
    private List<ModelChapter> chapters = new ArrayList<ModelChapter>();
    private int noStory;
    private ModelChapter tempModel;
    private PresenterChapter presenterStory;

    public FragmentChapters() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChapters.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChapters newInstance(String param1, String param2) {
        FragmentChapters fragment = new FragmentChapters();
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
        this.noStory = MainActivity.getNo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chapters, container, false);
        recycleView = view.findViewById(R.id.chapter_rv_1);
        recycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycleAdapter = new ChaptersRecycleAdapter(chapters);
        presenterStory = new PresenterChapterImpl(this);
        recycleView.setAdapter(recycleAdapter);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            MainActivity.setFragmentHandler(this);
            MainActivity.setCurrent_page(MainActivity.page.CHAPTERS);
            MainActivity.setButton();
        }
    }

    @Override
    public void showDialog(View v) {
        AppCompatDialog dialog = new AppCompatDialog(v.getContext());
        dialog.setContentView(R.layout.form_chapters);
        dialog.setTitle("Add New Chapter");
        EditText title = dialog.findViewById(R.id.form_chapter_title);
        Button tambah = dialog.findViewById(R.id.form_chapter_tambah);
        Button batal = dialog.findViewById(R.id.form_chapter_batal);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempModel = new ModelChapter(0, 0, title.getText().toString());
                presenterStory.save(tempModel);
                dialog.dismiss();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if(!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void onLoad(List<ModelChapter> chapters) {
        this.chapters.clear();
        this.chapters.addAll(chapters);
        recycleAdapter.notifyDataSetChanged();

    }

    @Override
    public void onSave() {
        presenterStory.load();
    }

    @Override
    public void onDelete() {

    }

    @Override
    public void onUpdate() {

    }
}