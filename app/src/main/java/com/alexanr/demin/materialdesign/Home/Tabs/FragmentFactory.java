package com.alexanr.demin.materialdesign.Home.Tabs;

import android.support.v4.app.Fragment;

public class FragmentFactory {

    private static final String[] TITLES = {"Photos", "Favorites Photos"};

    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return MainFragment.newInstance(null);
            case 1:
                return FavoritesFragment.newInstance(null);
            default:
                throw new IllegalArgumentException("Could not create fragment for position " + position);
        }
    }

    public int getFragmentsCount() {
        return TITLES.length;
    }

    public CharSequence getFragmentTitle(int position) {
        return TITLES[position];
    }
}
