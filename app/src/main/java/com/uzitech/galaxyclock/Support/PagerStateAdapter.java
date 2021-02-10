package com.uzitech.galaxyclock.Support;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.uzitech.galaxyclock.ClockFace.AmoledClock;
import com.uzitech.galaxyclock.ClockFace.DynamicClock;
import com.uzitech.galaxyclock.ClockFace.PhotoFrame;
import com.uzitech.galaxyclock.Fragment.ActionFragment;
import com.uzitech.galaxyclock.Fragment.AppFragment;
import com.uzitech.galaxyclock.Fragment.Clock2;
import com.uzitech.galaxyclock.Fragment.ClockFragment;
import com.uzitech.galaxyclock.R;

public class PagerStateAdapter extends FragmentStateAdapter {

    SharedPreferences preferences;

    public PagerStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        preferences = fragmentActivity.getSharedPreferences(String.valueOf(R.string.pref_data), Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ActionFragment();
            case 1:
                switch (preferences.getInt("clock_mode", 0)) {
                    case 0:
                        return new AmoledClock();
                    case 1:
                        return new DynamicClock();
                    case 2:
                        return new PhotoFrame();
                }
            case 2:
                return new AppFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
