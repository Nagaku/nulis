package com.michael.nulis;

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
import android.widget.TextView;
import android.widget.Toast;

import com.michael.nulis.adapter.ChaptersRecycleAdapter;
import com.michael.nulis.model.ModelChapter;
import com.michael.nulis.presenter.GlobalFragment;
import com.michael.nulis.presenter.PresenterChapter;
import com.michael.nulis.presenter.PresenterChapterImpl;
import com.michael.nulis.view.ViewChapter;

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
    private List<ModelChapter> long_chapters = new ArrayList<ModelChapter>();
    private int noStory;
    private ModelChapter tempModel;
    private PresenterChapter presenterChapter;

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
        recycleView.setAdapter(recycleAdapter);
        presenterChapter = new PresenterChapterImpl(this, noStory);
        this.noStory = MainActivity.getNo();
        recycleAdapter.setListener(new ChaptersRecycleAdapter.OnCallbackListener(){
            @Override
            public void onClick(ModelChapter chapter) {
                MainActivity.getFm().beginTransaction().hide(MainActivity.getFragmentActive()).show(MainActivity.getFragment5()).commit();
                MainActivity.setFragmentActive(MainActivity.fragment5);
                MainActivity.setNoChapter(chapter.getNoChapter());
            }
        });
        recycleAdapter.setListener_long(new ChaptersRecycleAdapter.OnLongCallbackListener() {
            @Override
            public void onClick(List<ModelChapter> chapter) {
                long_chapters = chapter;
            }
        });
        recycleView.setAdapter(recycleAdapter);
        return view;
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            MainActivity.setFragmentHandler(this);
            MainActivity.setCurrent_page(MainActivity.page.CHAPTERS);
            MainActivity.setButton();
            MainActivity.getJudul_page().setText(MainActivity.judul_story);
            this.noStory = MainActivity.getNo();
            presenterChapter = new PresenterChapterImpl(this, noStory);
            clean();
        }
    }

    @Override
    public void btnTambah(View v) {
        AppCompatDialog dialog = new AppCompatDialog(v.getContext());
        dialog.setContentView(R.layout.form_chapters);
        dialog.setTitle("Add New Chapter");
        TextView option = dialog.findViewById(R.id.form_chapter_option);
        option.setText("Tambah Chapter");
        EditText title = dialog.findViewById(R.id.form_chapter_title);
        Button tambah = dialog.findViewById(R.id.form_chapter_tambah);
        Button batal = dialog.findViewById(R.id.form_chapter_batal);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempModel = new ModelChapter(0, 0, noStory,title.getText().toString(), "");
                presenterChapter.save(tempModel);
                dialog.dismiss();
                MainActivity.toogle_button();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                MainActivity.toogle_button();
            }
        });

        if(!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void btnEdit(View view) {
        AppCompatDialog dialog = new AppCompatDialog(view.getContext());
        dialog.setContentView(R.layout.form_chapters);
        dialog.setTitle("Edit Chapter");
        TextView option = dialog.findViewById(R.id.form_chapter_option);
        option.setText("Edit Chapter");
        EditText title = dialog.findViewById(R.id.form_chapter_title);
        Button tambah = dialog.findViewById(R.id.form_chapter_tambah);
        Button batal = dialog.findViewById(R.id.form_chapter_batal);
        title.setText(long_chapters.get(0).getJudul());
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long_chapters.get(0).setJudul(title.getText().toString());
                presenterChapter.update(long_chapters.get(0));
                dialog.dismiss();
                recycleAdapter.clean();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                recycleAdapter.clean();
            }
        });

        if(!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void btnBack(View view) {

    }

    @Override
    public void btnHapus(View view) {
        AppCompatDialog dialog = new AppCompatDialog(view.getContext());
        dialog.setContentView(R.layout.form_chapters);
        TextView option = dialog.findViewById(R.id.form_chapter_option);
        option.setText("Delete Chapter");
        EditText title = dialog.findViewById(R.id.form_chapter_title);
        Button tambah = dialog.findViewById(R.id.form_chapter_tambah);
        Button batal = dialog.findViewById(R.id.form_chapter_batal);
        if ((long_chapters.size() -1 ) == 0)
            title.setText(long_chapters.get(0).getJudul() );
        else
        title.setText(long_chapters.get(0).getJudul() + " and " + (long_chapters.size() -1 ) + " others");
        title.setFocusable(false);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelChapter temp_chapter: long_chapters) {
                    presenterChapter.delete(temp_chapter);
                }
                dialog.dismiss();
                recycleAdapter.clean();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                recycleAdapter.clean();
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
        presenterChapter.load();
        Toast.makeText(getContext(), "Saved Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDelete() {
        presenterChapter.load();
        MainActivity.setIs_on_long(0);
        MainActivity.toogle_button();
        Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void onUpdate() {
        presenterChapter.load();
        Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void clean() {
        recycleAdapter.clean();
    }
}