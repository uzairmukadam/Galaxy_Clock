package com.uzitech.galaxyclock.Support;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.FrameLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;
import com.uzitech.galaxyclock.HomeDialog.DigitalClockDialog;

public class Clock {

    Activity activity;
    FrameLayout layout;

    TextView dayView, dateView;
    TextClock textClock;
    CustomAnalogClock analogClock;

    DigitalClockDialog digitalClockDialog;

    public Clock(Activity activity, FrameLayout layout, TextView dayView, TextView dateView) {
        this.activity = activity;
        this.layout = layout;
        this.dayView = dayView;
        this.dateView = dateView;
    }

    public void setDigitalClockSize(float size) {
        textClock.setTextSize(size);
    }

    public void setDigitalClockColor(int color) {
        textClock.setTextColor(color);
    }

    public void checkClockFormat() {
        if (textClock.is24HourModeEnabled()) {
            textClock.setFormat24Hour("kk:mm");
        } else {
            textClock.setFormat12Hour("hh:mm");
        }
    }

    public void createDigitalClock() {
        layout.removeAllViews();
        textClock = new TextClock(activity);
        textClock.setTextColor(ContextCompat.getColor(activity, android.R.color.white));
        textClock.setIncludeFontPadding(false);
        checkClockFormat();
        textClock.setTypeface(null, Typeface.BOLD);
        layout.addView(textClock);
        digitalClockDialog = new DigitalClockDialog(activity, textClock, dayView, dateView);
        textClock.setOnLongClickListener(v -> {
            digitalClockDialog.show();
            return true;
        });
    }

    public void createAnalogClock() {
        layout.removeAllViews();
        analogClock = new CustomAnalogClock(activity);
        analogClock.setAutoUpdate(true);
        layout.addView(analogClock);
        analogClock.setOnLongClickListener(v -> {
            Toast.makeText(activity, "Settings", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
