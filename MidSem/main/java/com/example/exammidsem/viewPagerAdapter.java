package com.example.exammidsem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPagerAdapter extends FragmentStateAdapter {
    List<Fragment> fragList=new ArrayList<>();
    List<String> titleTab=new ArrayList<>();
    public viewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragList.get(position);
    }

    String getTitle(int position){
        return titleTab.get(position);
    }

    void addFragment(Fragment frag, String title){
            fragList.add(frag);
            titleTab.add(title);
    }

    @Override
    public int getItemCount() {
        return fragList.size();
    }
}
