package com.example.jolebefood.fragment.tab_home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    ViewPager vp;

    private String uid;

    private MyPageFragment myPageFragment;
    private CategoryFragment categoryFragment;
    private DiscountFragment discountFragment;


    public ViewPagerAdapter(String uid,ViewPager vp, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.vp = vp;
        this.uid = uid;
        myPageFragment = new MyPageFragment(uid);
        categoryFragment = new CategoryFragment();
        discountFragment = new DiscountFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return myPageFragment;
            case 2:
                return new AccFragment(this.vp);
            case 3:
                return new CartFragment();
            case 4:
                return discountFragment;
            default: // 0
                return categoryFragment;
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
