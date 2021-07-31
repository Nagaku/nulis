package com.example.nulis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.nulis.adapter.MainRecycleAdapter;
import com.example.nulis.model.ModelStory;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static Fragment fragmentActive;
    private BottomNavigationView navigation;
    private FrameLayout frame;
    private static FragmentManager fm;
    final static Fragment fragment1 = new FragmentTulis();
    final static Fragment fragment2 = new FragmentExplore();
    final static Fragment fragment3 = new FragmentProfile();
    final static Fragment fragment4 = new FragmentChapters();
    private static int no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        contextOfApplication = getApplicationContext();

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
}