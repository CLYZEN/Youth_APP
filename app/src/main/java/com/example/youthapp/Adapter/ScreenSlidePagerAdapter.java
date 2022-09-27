package com.example.youthapp.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.youthapp.fragment_cung1;
import com.example.youthapp.fragment_moon2;

import java.util.ArrayList;

public class ScreenSlidePagerAdapter extends FragmentStateAdapter {



    ArrayList<Fragment> fragmentArrayList;

    public ScreenSlidePagerAdapter(@NonNull Fragment fragment, ArrayList<Fragment> fragmentArrayList) {
        super(fragment);
        this.fragmentArrayList = fragmentArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
}
