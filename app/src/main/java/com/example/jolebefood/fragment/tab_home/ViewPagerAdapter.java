package com.example.jolebefood.fragment.tab_home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new FavoriteFragment();
            case 2:
                return new MyPageFragment();
            default:
                return new RateFragment();
        }
    }

    @Override
    public int getCount() {
        return 3; // do có 3 tab
    }
}
