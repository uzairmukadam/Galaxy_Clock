package com.uzitech.galaxyclock.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.uzitech.galaxyclock.R;
import com.uzitech.galaxyclock.Support.BackgroundService;
import com.uzitech.galaxyclock.Support.DateUpdater;
import com.uzitech.galaxyclock.Support.Events;
import com.uzitech.galaxyclock.Support.GlobalBus;
import com.uzitech.galaxyclock.Support.PagerStateAdapter;

import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends AppCompatActivity {

    BackgroundService backgroundService;

    SharedPreferences preferences;
    ViewPager2 homePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GlobalBus.getEventBus().register(this);

        preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat("density", getResources().getDisplayMetrics().density);
        editor.apply();

        homePager = findViewById(R.id.homePager);
        homePager.setAdapter(new PagerStateAdapter(this));
        homePager.setCurrentItem(1, false);

        new DateUpdater(preferences);

        //if first run do setup

        backgroundService = new BackgroundService(this, findViewById(R.id.home_background), preferences);
    }

    @Subscribe
    public void getMode(Events.modeInfo modeInfo) {
        int val = modeInfo.getMessage();
        int pos = homePager.getCurrentItem();
        homePager.setAdapter(new PagerStateAdapter(this));
        homePager.setCurrentItem(pos, false);
        backgroundService.backgroundSelect(val);
    }

    @Override
    protected void onResume() {
        if (preferences.getInt("clock_mode", 0) == 2) {
            backgroundService.backgroundSelect(2);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        GlobalBus.getEventBus().unregister(this);
        super.onDestroy();
    }
}