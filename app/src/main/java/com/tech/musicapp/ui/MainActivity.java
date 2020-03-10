package com.tech.musicapp.ui;

import androidx.navigation.Navigation;

import android.os.Bundle;

import com.tech.musicapp.R;

import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
//            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.searchScreen);
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.playlistScreen);
        }
    }

}
