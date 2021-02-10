package com.uzitech.galaxyclock.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uzitech.galaxyclock.R;

import java.util.Objects;

public class Clock2 extends Fragment {

    View root;

    SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_clock2, container, false);

        preferences = Objects.requireNonNull(getActivity()).getSharedPreferences(String.valueOf(R.string.app_name), Context.MODE_PRIVATE);

        return root;
    }
}