package com.uzitech.galaxyclock.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.uzitech.galaxyclock.Fragment.ClockFragment;
import com.uzitech.galaxyclock.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void setClock1(View view) {
        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clock_mode", 0);
        editor.apply();
        finish();
    }

    public void setClock2(View view) {
        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clock_mode", 1);
        editor.apply();
        finish();
    }

    public void setClock3(View view) {
        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clock_mode", 2);
        editor.apply();
        finish();
    }

    public void setDigital(View view) {
        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clock_type", 0);
        editor.apply();
        finish();
    }

    public void setAnalog(View view) {
        SharedPreferences preferences = getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("clock_type", 1);
        editor.apply();
        finish();
    }
}