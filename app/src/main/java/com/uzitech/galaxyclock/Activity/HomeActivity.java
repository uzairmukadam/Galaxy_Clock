package com.uzitech.galaxyclock.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.uzitech.galaxyclock.R;
import com.uzitech.galaxyclock.Support.BackgroundService;
import com.uzitech.galaxyclock.Support.PagerStateAdapter;

public class HomeActivity extends AppCompatActivity {

    BackgroundService backgroundService;

    SharedPreferences preferences;
    public int curr_clock_mode;
    ViewPager2 homePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);

        curr_clock_mode = preferences.getInt("clock_mode", 0);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("density", getResources().getDisplayMetrics().density);
        editor.apply();

        //if first run do setup

        homePager = findViewById(R.id.homePager);
        homePager.setAdapter(new PagerStateAdapter(this));
        homePager.setCurrentItem(1, false);

        backgroundService = new BackgroundService(this, findViewById(R.id.home_background), preferences);
    }

    public void refreshPager() {
        curr_clock_mode = preferences.getInt("clock_mode", 0);
        int pos = homePager.getCurrentItem();
        homePager.setAdapter(new PagerStateAdapter(this));
        homePager.setCurrentItem(pos, false);
    }

    @Override
    protected void onResume() {
        if(curr_clock_mode!=preferences.getInt("clock_mode", 0)){
            refreshPager();
        }

        super.onResume();
    }
}