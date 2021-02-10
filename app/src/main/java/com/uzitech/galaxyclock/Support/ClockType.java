package com.uzitech.galaxyclock.Support;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.FrameLayout;
import android.widget.TextClock;

import com.tomerrosenfeld.customanalogclockview.CustomAnalogClock;

public class ClockType {

    Activity activity;
    FrameLayout layout;

    TextClock textClock;
    CustomAnalogClock analogClock;

    public ClockType(Activity activity, FrameLayout layout){
        this.activity = activity;
        this.layout = layout;
    }

    public void digitalSize(float size){
        textClock.setTextSize(size);
    }

    public void digitalColor(int color){
        textClock.setTextColor(color);
    }

    public void checkDigital(){
        if (textClock.is24HourModeEnabled()) {
            textClock.setFormat24Hour("HH:MM");
        } else {
            textClock.setFormat12Hour("HH:MM a");
        }
    }

    public void digitalClock(){
        layout.removeAllViews();
        textClock = new TextClock(activity);
        textClock.setIncludeFontPadding(false);
        if (textClock.is24HourModeEnabled()) {
            textClock.setFormat24Hour("HH:MM");
        } else {
            textClock.setFormat12Hour("HH:MM a");
        }
        textClock.setTypeface(null, Typeface.BOLD);
        layout.addView(textClock);
    }

    public void analogClock(){
        layout.removeAllViews();
        analogClock = new CustomAnalogClock(activity);
        analogClock.setAutoUpdate(true);
        layout.addView(analogClock);
    }
}
