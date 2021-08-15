package com.example.nulis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nulis.adapter.ChaptersRecycleAdapter;
import com.example.nulis.model.ModelChapter;
import com.example.nulis.model.ModelStory;
import com.example.nulis.presenter.GlobalFragment;
import com.example.nulis.presenter.PresenterChapter;
import com.example.nulis.presenter.PresenterChapterImpl;
import com.example.nulis.view.ViewChapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentChapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentChapter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int noChapter;
    private ModelChapter tempModel;
    private EditText isi;

    public FragmentChapter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentChapter.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentChapter newInstance(String param1, String param2) {
        FragmentChapter fragment = new FragmentChapter();
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
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        isi = view.findViewById(R.id.chapter_isi);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            MainActivity.setCurrent_page(MainActivity.page.CHAPTERS);
            MainActivity.toogle_button_chapter();
            this.noChapter = MainActivity.getNoChapter();
            tempModel = MainActivity.db.chapterDao().loadOneChapter(this.noChapter);
            MainActivity.getJudul_page().setText(tempModel.getJudul());
            isi.setText(tempModel.getIsi().toString());

            MainActivity.getButton_ok().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tempModel.setIsi(isi.getText().toString());
                    MainActivity.db.chapterDao().updateChapter(tempModel);
                    goBack();
                }
            });

            MainActivity.getButton_no().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goBack();
                }
            });
        }
    }

    private void goBack() {
        MainActivity.getFm().beginTransaction().hide(MainActivity.getFragmentActive()).show(MainActivity.getFragment4()).commit();
        MainActivity.setFragmentActive(MainActivity.fragment4);
        MainActivity.toogle_button_chapter();
    }

}