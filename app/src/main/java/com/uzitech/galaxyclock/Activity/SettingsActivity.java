package com.uzitech.galaxyclock.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.uzitech.galaxyclock.R;
import com.uzitech.galaxyclock.Support.Events;
import com.uzitech.galaxyclock.Support.GlobalBus;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    SharedPreferences preferences;

    int curr_mode, curr_type;

    ArrayList<RadioButton> mode_group, type_group;
    RadioButton mode_1, mode_2, mode_3;
    RadioButton type_1, type_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);

        curr_mode = preferences.getInt("clock_mode", 0);
        curr_type = preferences.getInt("clock_type", 0);

        createModeSelection();

        createTypeSelection();
    }

    private void selectType(View view) {
        for (RadioButton button : type_group) {
            button.setChecked(button == view);
        }
    }

    private void createTypeSelection() {
        type_group = new ArrayList<>();
        type_1 = findViewById(R.id.clock_type1);
        type_2 = findViewById(R.id.clock_type2);
        type_group.add(type_1);
        type_group.add(type_2);

        type_1.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clock_type", 0);
            editor.apply();
            Events.clockInfo clockInfo = new Events.clockInfo(0);
            GlobalBus.getEventBus().post(clockInfo);
            selectType(v);
        });

        type_2.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clock_type", 1);
            editor.apply();
            Events.clockInfo clockInfo = new Events.clockInfo(1);
            GlobalBus.getEventBus().post(clockInfo);
            selectType(v);
        });

        switch (curr_type) {
            case 0:
                type_1.setChecked(true);
                break;
            case 1:
                type_2.setChecked(true);
                break;
        }
    }

    private void selectMode(View view) {
        for (RadioButton button : mode_group) {
            button.setChecked(button == view);
        }
    }

    private void createModeSelection() {
        mode_group = new ArrayList<>();
        mode_1 = findViewById(R.id.clock_mode1);
        mode_2 = findViewById(R.id.clock_mode2);
        mode_3 = findViewById(R.id.clock_mode3);
        mode_group.add(mode_1);
        mode_group.add(mode_2);
        mode_group.add(mode_3);

        mode_1.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clock_mode", 0);
            editor.apply();
            Events.modeInfo modeInfo = new Events.modeInfo(0);
            GlobalBus.getEventBus().post(modeInfo);
            selectMode(v);
        });

        mode_2.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clock_mode", 1);
            editor.apply();
            Events.modeInfo modeInfo = new Events.modeInfo(1);
            GlobalBus.getEventBus().post(modeInfo);
            selectMode(v);
        });

        mode_3.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("clock_mode", 2);
            editor.apply();
            Events.modeInfo modeInfo = new Events.modeInfo(2);
            GlobalBus.getEventBus().post(modeInfo);
            selectMode(v);
        });

        switch (curr_mode) {
            case 0:
                mode_1.setChecked(true);
                break;
            case 1:
                mode_2.setChecked(true);
                break;
            case 2:
                mode_3.setChecked(true);
        }
    }
}