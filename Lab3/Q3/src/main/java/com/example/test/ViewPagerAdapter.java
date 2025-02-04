package com.example.test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new com.example.newsapp.TopStoriesFragment();
            case 1:
                return new com.example.newsapp.SportsFragment();
            case 2:
                return new com.example.newsapp.EntertainmentFragment();
            default:
                return new com.example.newsapp.TopStoriesFragment();
        }
    }


    @Override
    public int getCount() {
        return 3; // Number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Top Stories";
            case 1:
                return "Sports";
            case 2:
                return "Entertainment";
            default:
                return "Tab " + (position + 1);
        }
    }


}
