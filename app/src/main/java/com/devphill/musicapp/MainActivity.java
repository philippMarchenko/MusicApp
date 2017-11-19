package com.devphill.musicapp;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.devphill.musicapp.ui.fragments.MiniPlayerFragment;


public class MainActivity extends AppCompatActivity {


    String LOG_TAG = "MainActivityTag";

    FragmentTransaction ft;

    MiniPlayerFragment miniPlayerFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miniPlayerFragment = MiniPlayerFragment.newInstance();

        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, miniPlayerFragment,"miniPlayerFragment");
        ft.commit();
        Log.d(LOG_TAG,"onCreate");
    }

}
