package com.uzitech.galaxyclock.Support;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Handler;

public class BackgroundService {

    Activity activity;
    ConstraintLayout layout;
    SharedPreferences preferences;

    Handler dynamicBack_handler;
    Runnable dynamicBack_runnable;

    public BackgroundService(Activity activity, ConstraintLayout layout, SharedPreferences preferences) {
        this.activity = activity;
        this.layout = layout;
        this.preferences = preferences;

        backgroundSelect(preferences.getInt("clock_mode", 0));
    }

    public void backgroundSelect(int curr_mode) {
        if (curr_mode == 0) {
            layout.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.black));
        } else if (curr_mode == 1) {
            layout.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.holo_blue_light));
        } else if (curr_mode == 2) {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(activity);
            final Drawable wallpaperDrawable = wallpaperManager.getDrawable();
            layout.setBackground(wallpaperDrawable);
        }
    }

    private void dynamicBackground(){
        dynamicBack_handler = new Handler();
    }
}
