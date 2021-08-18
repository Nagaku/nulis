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

import com.michael.nulis.adapter.MainRecycleAdapter;
import com.michael.nulis.model.ModelStory;
import com.michael.nulis.presenter.GlobalFragment;
import com.michael.nulis.presenter.PresenterStory;
import com.michael.nulis.presenter.PresenterStoryImpl;
import com.michael.nulis.view.ViewStory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTulis#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTulis extends Fragment implements ViewStory, GlobalFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recycleView;
    private MainRecycleAdapter recycleAdapter;
    private List<ModelStory> stories = new ArrayList<ModelStory>();
    private List<ModelStory> long_stories = new ArrayList<ModelStory>();
    private ModelStory tempModel;
    private PresenterStory presenterStory;

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
        MainActivity.setFragmentHandler(this);
        MainActivity.setCurrent_page(MainActivity.page.STORY);
        MainActivity.setButton();
        View view = inflater.inflate(R.layout.fragment_tulis, container, false);
        recycleView = view.findViewById(R.id.main_rv_1);
        recycleView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recycleAdapter = new MainRecycleAdapter(stories);
        presenterStory = new PresenterStoryImpl(this);
        recycleAdapter.setListener(new MainRecycleAdapter.OnCallbackListener(){
            @Override
            public void onClick(ModelStory story) {
                MainActivity.getFm().beginTransaction().hide(MainActivity.getFragmentActive()).show(MainActivity.getFragment4()).commit();
                MainActivity.setFragmentActive(MainActivity.fragment4);
                MainActivity.setNo(story.getNoStory());
                MainActivity.judul_story = story.getJudul().toString();
            }
        });
        recycleAdapter.setListener_long(new MainRecycleAdapter.OnLongCallbackListener() {
            @Override
            public void onClick(List<ModelStory> story) {
                long_stories = story;
            }
        });
        recycleView.setAdapter(recycleAdapter);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            MainActivity.setFragmentHandler(this);
            MainActivity.setCurrent_page(MainActivity.page.STORY);
            MainActivity.setButton();
            MainActivity.getJudul_page().setText("Story");
        } else {
            clean();
        }
    }

    @Override
    public void onLoad(List<ModelStory> stories) {
        this.stories.clear();
        this.stories.addAll(stories);
        recycleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSave() {
        presenterStory.load();
        Toast.makeText(MainActivity.getContextOfApplication(), "Saved Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDelete() {
        presenterStory.load();
        MainActivity.setIs_on_long(0);
        MainActivity.toogle_button();
        Toast.makeText(MainActivity.getContextOfApplication(), "Deleted Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void onUpdate() {
        presenterStory.load();
        Toast.makeText(MainActivity.getContextOfApplication(), "Updated Successfully", Toast.LENGTH_SHORT);
    }

    @Override
    public void btnTambah(View v) {
        AppCompatDialog dialog = new AppCompatDialog(v.getContext());
        dialog.setContentView(R.layout.form_story);
        dialog.setTitle("Add New Story");
        TextView option = dialog.findViewById(R.id.form_story_option);
        option.setText("Add Story");
        EditText title = dialog.findViewById(R.id.form_story_title);
        Button tambah = dialog.findViewById(R.id.form_story_tambah);
        Button batal = dialog.findViewById(R.id.form_story_batal);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempModel = new ModelStory(title.getText().toString(), "Self");
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
    public void btnEdit(View view) {

        AppCompatDialog dialog = new AppCompatDialog(view.getContext());
        dialog.setContentView(R.layout.form_story);
        dialog.setTitle("Edit Story");
        TextView option = dialog.findViewById(R.id.form_story_option);
        option.setText("Edit Story");
        EditText title = dialog.findViewById(R.id.form_story_title);
        Button tambah = dialog.findViewById(R.id.form_story_tambah);
        Button batal = dialog.findViewById(R.id.form_story_batal);
        title.setText(long_stories.get(0).getJudul());
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long_stories.get(0).setJudul(title.getText().toString());
                presenterStory.update(long_stories.get(0));
                dialog.dismiss();
                recycleAdapter.clean();
                MainActivity.toogle_button();
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                recycleAdapter.clean();
                MainActivity.toogle_button();
            }
        });

        if(!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void btnHapus(View view) {
        AppCompatDialog dialog = new AppCompatDialog(view.getContext());
        dialog.setContentView(R.layout.form_story);
        dialog.setTitle("Delete Story");
        TextView option = dialog.findViewById(R.id.form_story_option);
        option.setText("Delete Story");
        EditText title = dialog.findViewById(R.id.form_story_title);
        Button tambah = dialog.findViewById(R.id.form_story_tambah);
        Button batal = dialog.findViewById(R.id.form_story_batal);
        if ((long_stories.size() -1 ) == 0)
            title.setText(long_stories.get(0).getJudul() );
        else
        title.setText(long_stories.get(0).getJudul() + " and " + (long_stories.size() -1 ) + " others");
        title.setFocusable(false);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelStory temp_story: long_stories) {
                    presenterStory.delete(temp_story);
                    recycleAdapter.clean();
                }
                dialog.dismiss();
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
    public void clean() {
        recycleAdapter.clean();
    }
}