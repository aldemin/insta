package com.alexanr.demin.materialdesign.Home.Tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomFragmentAdapter extends FragmentPagerAdapter {
    private final FragmentFactory factory;

    public CustomFragmentAdapter(FragmentManager fm, FragmentFactory factory) {
        super(fm);
        this.factory = factory;
    }

    @Override
    public Fragment getItem(int position) {
        return factory.createFragment(position);
    }

    @Override
    public int getCount() {
        return factory.getFragmentsCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return factory.getFragmentTitle(position);
    }
}
