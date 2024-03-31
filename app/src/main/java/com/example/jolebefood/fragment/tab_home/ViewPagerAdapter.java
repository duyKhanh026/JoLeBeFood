package com.example.jolebefood.fragment.tab_home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ViewPager vp;
    public ViewPagerAdapter(ViewPager vp, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.vp = vp;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return new MyPageFragment();
            case 2:
                return new AccFragment(this.vp);
            case 3:
                return new CartFragment();
            case 4:
                return new DiscountFragment();
            default: // 0
                return new CategoryFragment();
        }
    }

    @Override
    public int getCount() {
        return 5; // do có 6 tab
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    
}
