package com.uzitech.galaxyclock.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uzitech.galaxyclock.R;

import java.util.Objects;

public class ClockFragment extends Fragment {

    View root;

    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_clock, container, false);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);

        return root;
    }
}