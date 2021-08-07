package com.example.nulis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.nulis.adapter.MainRecycleAdapter;
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
    private static int no = 0;
    private static page current_page = page.STORY;
    private static GlobalFragment fragmentHandler;

    private static Button button_tambah;
    private static Button button_hapus;
    private static Button button_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();
        button_tambah = findViewById(R.id.main_btn_tmbh);
        button_hapus = findViewById(R.id.main_btn_hapus);
        button_edit = findViewById(R.id.main_btn_update);
        frame = findViewById(R.id.frame);
        navigation = findViewById(R.id.main_bnv_1);
        fm = getSupportFragmentManager();
        fragmentActive = fragment1;
        fm.beginTransaction().add(R.id.frame, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.frame, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.frame, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame, fragment1, "1").commit();

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_tulis:
                        Log.d("menu", "menu1");
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
        fragmentActive = fragmentActive;
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
        switch (getCurrent_page()) {
            case STORY:
                button_tambah.setOnClickListener(null);
                button_tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentHandler.showDialog(v);
                    }
                });

                break;
            case CHAPTERS:
                button_tambah.setOnClickListener(null);
                button_tambah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fragmentHandler.showDialog(v);
                    }
                });
                break;
            case CHAPTER:
                break;
            case PROFILE:
                break;
        }
    }

    public static Button getButton_tambah() {
        return button_tambah;
    }
}