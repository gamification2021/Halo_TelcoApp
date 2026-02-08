package com.example.telcoapp.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.telcoapp.fragment.PredictGameFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public TabPagerAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PredictGameFragment("all");
            case 1:
                return new PredictGameFragment("football");
            case 2:
                return new PredictGameFragment("cricket");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
