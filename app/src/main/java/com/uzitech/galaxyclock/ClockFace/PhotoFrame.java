package com.uzitech.galaxyclock.ClockFace;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.uzitech.galaxyclock.R;
import com.uzitech.galaxyclock.Support.ClockType;

import java.util.Objects;

public class PhotoFrame extends Fragment {

    View root;

    SharedPreferences preferences;

    ClockType clockType;

    int clock_type;

    FrameLayout clockFrame;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_photo_frame, container, false);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);

        clock_type = preferences.getInt("clock_type", 0);

        clockFrame = root.findViewById(R.id.clock_frame);

        clockType = new ClockType(getActivity(), clockFrame);

        if (clock_type == 0) {
            clockType.digitalClock();
            //clockType.digitalColor(ContextCompat.getColor(getActivity(), android.R.color.white));
            clockType.digitalSize(96);
        } else {
            clockType.analogClock();
        }

        return root;
    }

    @Override
    public void onResume() {
        if (clock_type != preferences.getInt("clock_type", 0)) {
            if (preferences.getInt("clock_type", 0) == 0) {
                clock_type = preferences.getInt("clock_type", 0);
                clockType.digitalClock();
                //clockType.digitalColor(ContextCompat.getColor(getActivity(), android.R.color.white));
                clockType.digitalSize(128);
            } else {
                clock_type = preferences.getInt("clock_type", 1);
                clockType.analogClock();
            }
        }

        if (clock_type == 0) {
            clockType.checkDigital();
        }

        super.onResume();
    }
}