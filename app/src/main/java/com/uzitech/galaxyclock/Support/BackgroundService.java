package com.uzitech.galaxyclock.Support;

import android.app.Activity;
import android.content.SharedPreferences;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class BackgroundService {

    Activity activity;
    ConstraintLayout layout;
    SharedPreferences preferences;

    int curr_mode;

    public BackgroundService(Activity activity, ConstraintLayout layout, SharedPreferences preferences){
        this.activity = activity;
        this.layout = layout;
        this.preferences = preferences;

        curr_mode = preferences.getInt("clock_mode", 0);

        backgroundSelect();
    }

    private void backgroundSelect(){
        if(curr_mode==0){
            layout.setBackgroundColor(ContextCompat.getColor(activity, android.R.color.black));
        }
    }
}
