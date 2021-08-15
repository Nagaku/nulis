package com.example.nulis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nulis.adapter.MainRecycleAdapter;
import com.example.nulis.model.AppDatabase;
import com.example.nulis.model.ModelStory;
import com.example.nulis.presenter.GlobalFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    enum page {
        STORY,
        CHAPTERS,
        CHAPTER,
        PROFILE,
    }

    private static Fragment fragmentActive;
    private BottomNavigationView navigation;
    private FrameLayout frame;
    private static FragmentManager fm;
    final static Fragment fragment1 = new FragmentTulis();
    final static Fragment fragment2 = new FragmentExplore();
    final static Fragment fragment3 = new FragmentProfile();
    final static Fragment fragment4 = new FragmentChapters();
    final static Fragment fragment5 = new FragmentChapter();
    private static int no = 0;
    private static int noChapter = 0;
    private static page current_page = page.STORY;
    private static GlobalFragment fragmentHandler;
    public static AppDatabase db;

    private static Button button_tambah;
    private static Button button_hapus;
    private static Button button_edit;
    private static Button button_back;
    private static Button button_ok;
    private static Button button_no;
    private static int is_on_long = 0;

    private static TextView judul_page;
    public static String judul_story;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"story")
                .allowMainThreadQueries().build();
        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();
        button_tambah = findViewById(R.id.main_btn_tmbh);
        button_hapus = findViewById(R.id.main_btn_hapus);
        button_edit = findViewById(R.id.main_btn_update);
        button_back = findViewById(R.id.main_btn_back);
        button_ok = findViewById(R.id.main_btn_ok);
        button_no = findViewById(R.id.main_btn_no);
        judul_page = findViewById(R.id.main_title);
        judul_page.setText("Story");
        frame = findViewById(R.id.frame);
        navigation = findViewById(R.id.main_bnv_1);
        fm = getSupportFragmentManager();
        fragmentActive = fragment1;
        fm.beginTransaction().add(R.id.frame, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.frame, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.frame, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.frame, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame, fragment1, "1").commit();

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_tulis:
                        Log.d("menu", fragmentActive.getClass().toString());
                        fm.beginTransaction().hide(fragmentActive).show(fragment1).commit();
                        fragmentActive = fragment1;
                        return true;
                    case R.id.menu_explore:
                        fm.beginTransaction().hide(fragmentActive).show(fragment2).commit();
                        fragmentActive = fragment2;
                        return true;
                    case R.id.menu_profile:
                        fm.beginTransaction().hide(fragmentActive).show(fragment3).commit();
                        fragmentActive = fragment3;
                        setCurrent_page(page.PROFILE);
                        return true;
                }
                return false;
            }
        });

    }

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    public static FragmentManager getFm() {
        return fm;
    }

    public static Fragment getFragmentActive() {
        return fragmentActive;
    }

    public static void setFragmentActive(Fragment fragmentActive) {
        MainActivity.fragmentActive = fragmentActive;
    }

    public static void setFm(FragmentManager fm) {
        fm = fm;
    }

    public static Fragment getFragment1() {
        return fragment1;
    }

    public static Fragment getFragment2() {
        return fragment2;
    }

    public static Fragment getFragment3() {
        return fragment3;
    }

    public static Fragment getFragment4() {
        return fragment4;
    }

    public static Fragment getFragment5() {
        return fragment5;
    }

    public static int getNo() {
        return no;
    }

    public static void setNo(int no) {
        MainActivity.no = no;
    }

    public static page getCurrent_page() {
        return current_page;
    }

    public static void setCurrent_page(page current_page) {
        MainActivity.current_page = current_page;
    }

    public static GlobalFragment getFragmentHandler() {
        return fragmentHandler;
    }

    public static void setFragmentHandler(GlobalFragment fragmentHandler) {
        MainActivity.fragmentHandler = fragmentHandler;
    }

    public static void setButton() {
        button_tambah.setOnClickListener(null);
        button_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentHandler.btnTambah(v);
            }
        });

        button_edit.setOnClickListener(null);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentHandler.btnEdit(v);
            }
        });

        button_hapus.setOnClickListener(null);
        button_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentHandler.btnHapus(v);
            }
        });
    }

    public static Button getButton_tambah() {
        return button_tambah;
    }

    public static Button getButton_hapus() {
        return button_hapus;
    }

    public static void setButton_hapus() {
    }

    public static Button getButton_edit() {
        return button_edit;
    }

    public static void setButton_edit() {
    }

    public static Button getButton_back() {
        return button_back;
    }

    public static void setButton_back(Button button_back) {
        MainActivity.button_back = button_back;
    }

    public static TextView getJudul_page() {
        return judul_page;
    }

    public static void setJudul_page(TextView judul_page) {
        MainActivity.judul_page = judul_page;
    }

    public static Button getButton_ok() {
        return button_ok;
    }

    public static Button getButton_no() {
        return button_no;
    }

    public static int getIs_on_long() {
        return MainActivity.is_on_long;
    }

    public static void setIs_on_long(int is_on_long) {
        MainActivity.is_on_long = is_on_long;
    }

    public static int getNoChapter() {
        return noChapter;
    }

    public static void setNoChapter(int noChapter) {
        MainActivity.noChapter = noChapter;
    }

    public static void toogle_button_chapter() {
        if ((MainActivity.getButton_tambah().getVisibility() == View.VISIBLE) || MainActivity.getButton_edit().getVisibility() == View.VISIBLE ) {
            MainActivity.getButton_tambah().setVisibility(View.INVISIBLE);
            MainActivity.getButton_edit().setVisibility(View.INVISIBLE);
            MainActivity.getButton_hapus().setVisibility(View.INVISIBLE);
            MainActivity.getButton_back().setVisibility(View.INVISIBLE);
            button_ok.setVisibility(View.VISIBLE);
            button_no.setVisibility(View.VISIBLE);
        } else {
            MainActivity.getButton_tambah().setVisibility(View.VISIBLE);
            button_ok.setVisibility(View.INVISIBLE);
            button_no.setVisibility(View.INVISIBLE);
        }
    }

    public static void hide_button() {

    }

    public static void toogle_button() {
        if ((MainActivity.getButton_tambah().getVisibility() == View.VISIBLE) && (MainActivity.getIs_on_long() > 0)) {
            MainActivity.getButton_tambah().setVisibility(View.INVISIBLE);
            MainActivity.getButton_edit().setVisibility(View.VISIBLE);
            MainActivity.getButton_hapus().setVisibility(View.VISIBLE);
            MainActivity.getButton_back().setVisibility(View.VISIBLE);
        } else {
            if (MainActivity.getIs_on_long() < 1) {
                Log.d("back_1", "readding button + " + getCurrent_page());
                MainActivity.getButton_tambah().setVisibility(View.VISIBLE);
                MainActivity.getButton_edit().setVisibility(View.GONE);
                MainActivity.getButton_hapus().setVisibility(View.GONE);
                MainActivity.getButton_back().setVisibility(View.GONE);

                switch (getCurrent_page()) {
                    case STORY:
                        Log.d("back", "balik");
                        MainRecycleAdapter.unselect();
                        break;
                    case CHAPTERS:
                        break;
                    case CHAPTER:
                        break;
                    case PROFILE:
                        break;
                }
            }
        }


        if (MainActivity.getIs_on_long() == 2) {
            MainActivity.getButton_edit().setVisibility(View.GONE);
            MainActivity.getButton_back().setLeft(R.id.main_btn_hapus);
        }
    }
}