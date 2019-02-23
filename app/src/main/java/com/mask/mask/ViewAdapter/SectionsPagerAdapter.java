package com.mask.mask.ViewAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * 三个主碎片更新页面
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    FragmentManager mFragmentManager;
    public SectionsPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
        mFragmentManager = fm;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = fragmentList.get(position);// 获取要销毁的fragment
        mFragmentManager.beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }
}
