package com.mask.mask.ViewAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments;
    public SectionsPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
