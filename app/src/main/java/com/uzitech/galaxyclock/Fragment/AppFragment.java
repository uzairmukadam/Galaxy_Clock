package com.uzitech.galaxyclock.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uzitech.galaxyclock.Activity.SettingsActivity;
import com.uzitech.galaxyclock.R;

public class AppFragment extends Fragment {

    Button settings;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_app, container, false);

        settings = root.findViewById(R.id.sett);

        settings.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingsActivity.class)));

        return root;
    }
}