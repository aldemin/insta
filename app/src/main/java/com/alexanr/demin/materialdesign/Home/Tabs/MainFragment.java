package com.alexanr.demin.materialdesign.Home.Tabs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.alexanr.demin.materialdesign.Home.Tabs.ButtomBar.AllPhotoFragment;
import com.alexanr.demin.materialdesign.Home.Tabs.ButtomBar.DbPhotoFragment;
import com.alexanr.demin.materialdesign.Home.Tabs.ButtomBar.ServerPhotoFragment;
import com.alexanr.demin.materialdesign.R;

public class MainFragment extends Fragment {

    public static final String FRAGMENT_ALL = "fragment_all";
    public static final String FRAGMENT_DB = "fragment_database";
    public static final String FRAGMENT_INTERNET = "fragment_internet";


    private BottomNavigationView bottomNavigationView;
    private Bundle bundle;

    public static MainFragment newInstance(Bundle bundle) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        if (getChildFragmentManager().findFragmentByTag(FRAGMENT_ALL) == null) {
            getChildFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, AllPhotoFragment.newInstance(bundle), FRAGMENT_ALL)
                    .commit();
        }
        bottomNavigationView = root.findViewById(R.id.bottom_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.bottom_menu_all:
                        fragment = getChildFragmentManager().findFragmentByTag(FRAGMENT_ALL);
                        if (fragment == null) {
                            fragment = AllPhotoFragment.newInstance(bundle);
                        }
                        getChildFragmentManager()
                                .beginTransaction()
                                //.replace(R.id.main_container, AllPhotoFragment.newInstance(bundle), FRAGMENT_ALL)
                                .replace(R.id.main_container, fragment, FRAGMENT_ALL)
                                .commit();
                        return true;
                    case R.id.bottom_menu_database:
                        fragment = getChildFragmentManager().findFragmentByTag(FRAGMENT_DB);
                        if (fragment == null) {
                            fragment = DbPhotoFragment.newInstance(bundle);
                        }
                        getChildFragmentManager()
                                .beginTransaction()
                                //.replace(R.id.main_container, DbPhotoFragment.newInstance(bundle), FRAGMENT_DB)
                                .replace(R.id.main_container, fragment, FRAGMENT_DB)
                                .commit();
                        return true;
                    case R.id.bottom_menu_internet:
                        fragment = getChildFragmentManager().findFragmentByTag(FRAGMENT_INTERNET);
                        if (fragment == null) {
                            fragment = ServerPhotoFragment.newInstance(bundle);
                        }
                        getChildFragmentManager()
                                .beginTransaction()
                                //.replace(R.id.main_container, ServerPhotoFragment.newInstance(bundle), FRAGMENT_INTERNET)
                                .replace(R.id.main_container, fragment, FRAGMENT_INTERNET)
                                .commit();
                        return true;
                }
                return false;
            }
        });
        return root;
    }
}
