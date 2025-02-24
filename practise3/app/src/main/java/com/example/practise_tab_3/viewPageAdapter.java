package com.example.practise_tab_3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class viewPageAdapter extends FragmentStateAdapter {
    public List<Fragment> FragmentList= new ArrayList<>();
    public List<String> TitleList= new ArrayList<>();
    public viewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    void addFragment(Fragment frag, String title){
        FragmentList.add(frag);
        TitleList.add(title);
    }

    String  getTitle(int position){
        return TitleList.get(position);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return FragmentList.size();
    }
}
