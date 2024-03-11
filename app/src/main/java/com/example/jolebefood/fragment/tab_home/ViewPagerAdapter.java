package com.example.jolebefood.fragment.tab_home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new MyPageFragment();
            case 2:
                return new FavoriteFragment();
            case 3:
                return new CartFragment();
            case 4:
                return new KhuyenMaiFragment();
            default: // 0
                return new ProductFragment();
        }
    }

    @Override
    public int getCount() {
        return 5; // do có 4 tab
    }
}
