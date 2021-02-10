package com.uzitech.galaxyclock.ClockFace;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.uzitech.galaxyclock.R;
import com.uzitech.galaxyclock.Support.Clock;
import com.uzitech.galaxyclock.Support.Events;
import com.uzitech.galaxyclock.Support.GlobalBus;

import org.greenrobot.eventbus.Subscribe;

import java.util.Objects;

public class AmoledClock extends Fragment {

    View root;

    SharedPreferences preferences;

    Clock clock;

    TextView day_view, date_view;

    FrameLayout clockFrame;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_amoled_clock, container, false);

        GlobalBus.getEventBus().register(this);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);

        clockFrame = root.findViewById(R.id.clock_frame);

        day_view = root.findViewById(R.id.day_view);
        date_view = root.findViewById(R.id.date_view);

        clock = new Clock(getActivity(), clockFrame, day_view, date_view);

        String date = preferences.getString("today_date", "-,-");
        day_view.setText(date.split(",")[0]);
        date_view.setText(date.split(",")[1]);


        if (preferences.getInt("clock_type", 0) == 0) {
            clock.createDigitalClock();
            clock.setDigitalClockColor(ContextCompat.getColor(getActivity(), android.R.color.white));
            clock.setDigitalClockSize(128);
        } else {
            clock.createAnalogClock();
        }

        return root;
    }

    @Subscribe
    public void getDate(Events.dateInfo dateInfo) {
        String val = dateInfo.getMessage();
        day_view.setText(val.split(",")[0]);
        date_view.setText(val.split(",")[1]);
    }


    @Subscribe
    public void getClockType(Events.clockInfo clockInfo) {
        int val = clockInfo.getMessage();
        if (val == 0) {
            clock.createDigitalClock();
            clock.setDigitalClockColor(ContextCompat.getColor(getActivity(), android.R.color.white));
            clock.setDigitalClockSize(128);
        } else {
            clock.createAnalogClock();
        }
    }

    @Override
    public void onDestroy() {
        GlobalBus.getEventBus().unregister(this);
        super.onDestroy();
    }
}