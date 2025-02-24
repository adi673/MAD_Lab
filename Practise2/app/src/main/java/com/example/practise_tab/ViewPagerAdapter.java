package com.example.practise_tab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    List <Fragment> fragmentList=new ArrayList<>();
    List <String> fragmetnTitleList= new ArrayList<>();
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addFragment(Fragment frag, String Title){
        fragmentList.add(frag);
        fragmetnTitleList.add(Title);
    }

    public  String getTitle(int position){
        return fragmetnTitleList.get(position);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
