package com.michael.nulis;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.michael.nulis.model.ModelQuicky;
import com.michael.nulis.presenter.PresenterQuicky;
import com.michael.nulis.presenter.PresenterQuickyImpl;
import com.michael.nulis.view.ViewQuicky;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentExplore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentExplore extends Fragment implements ViewQuicky {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText isi;
    private PresenterQuicky pq;
    private ModelQuicky mq;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentExplore() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentExplore.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentExplore newInstance(String param1, String param2) {
        FragmentExplore fragment = new FragmentExplore();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            MainActivity.getJudul_page().setText("Quicky");
            pq = new PresenterQuickyImpl(this);
            MainActivity.setCurrent_page(MainActivity.page.EXPLORE);
            MainActivity.hide_button();
            MainActivity.hide_all();
        } else {
            this.mq.setIsi(isi.getText().toString());
            pq.update(this.mq);
            MainActivity.hide_button();
            MainActivity.toogle_button_chapter();
        }
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
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        isi = view.findViewById(R.id.quicky);
        pq = new PresenterQuickyImpl(this);
        mq = new ModelQuicky(1, "");
        return view;
    }

    @Override
    public void onLoad(ModelQuicky mq) {
        this.mq = mq;
        isi.setText(mq.getIsi().toString());
    }


    @Override
    public void onUpdate() {

        Toast.makeText(getContext(), "Updated Successfully", Toast.LENGTH_SHORT);
        pq.load();
    }
}