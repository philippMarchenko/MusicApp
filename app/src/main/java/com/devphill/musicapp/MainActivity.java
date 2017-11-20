package com.devphill.musicapp;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.devphill.musicapp.ui.fragments.FragmentOne;
import com.devphill.musicapp.ui.fragments.FragmentTwo;
import com.devphill.musicapp.ui.fragments.MiniPlayerFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    String LOG_TAG = "MainActivityTag";

    FragmentTransaction ft;

    MiniPlayerFragment miniPlayerFragment;

    ViewPagerAdapter adapter;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miniPlayerFragment = new MiniPlayerFragment();

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().hide();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);




        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, miniPlayerFragment,"miniPlayerFragment");
        ft.commit();
        Log.d(LOG_TAG,"onCreate");

    }
    private void setupViewPager(ViewPager viewPager) {

        FragmentOne fragmentOne = new FragmentOne();
        adapter.addFragment(fragmentOne, "fragmentOne");

        FragmentTwo fragmentTwo = new FragmentTwo();
        adapter.addFragment(fragmentTwo, "fragmentTwo");

        viewPager.setAdapter(adapter);

    }


    @Override
    public void onResume() {
        super.onResume();

        Log.i(LOG_TAG, " onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(LOG_TAG, " onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(LOG_TAG, " onDestroy");
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }

        public void clearAdapter(){

            mFragmentList.clear();
            mFragmentTitleList.clear();
        }

    }
}
