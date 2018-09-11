package com.alexanr.demin.materialdesign.Home;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alexanr.demin.materialdesign.Constants;
import com.alexanr.demin.materialdesign.Home.Tabs.CustomFragmentAdapter;
import com.alexanr.demin.materialdesign.Home.Tabs.FragmentFactory;
import com.alexanr.demin.materialdesign.Home.Tabs.MainFragment;
import com.alexanr.demin.materialdesign.R;
import com.alexanr.demin.materialdesign.Settings.Settings;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private String currentTheme;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme(savedInstanceState);
        setCurrentTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.main_container,MainFragment.newInstance(null)).commit();
        }
        toolbar = findViewById(R.id.main_toolbar);
        drawer = findViewById(R.id.main_drawer);
        navigationView = findViewById(R.id.main_nav_view);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FragmentFactory fragmentFactory = new FragmentFactory();
        CustomFragmentAdapter customFragmentAdapter = new CustomFragmentAdapter(getSupportFragmentManager(),fragmentFactory);

        viewPager = findViewById(R.id.main_container);
        viewPager.setAdapter(customFragmentAdapter);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.drawer_menu_settings) {
            startActivity(new Intent(this, Settings.class));
        }
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setCurrentTheme() {
        if (currentTheme.equals(Constants.THEME_RED_DEFAULT)) {
            setTheme(R.style.RedTheme);
        } else if (currentTheme.equals(Constants.THEME_GREEN)) {
            setTheme(R.style.GreenTheme);
        } else if (currentTheme.equals(Constants.THEME_YELLOW)) {
            setTheme(R.style.YellowTheme);
        }
    }

    private void initTheme(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .getString(Constants.THEME, Constants.EMPTY_STRING)
                    .equals(Constants.EMPTY_STRING)) {
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                        .edit()
                        .putString(Constants.THEME, Constants.THEME_RED_DEFAULT)
                        .apply();
                currentTheme = Constants.THEME_RED_DEFAULT;
            }
        }
        currentTheme = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(Constants.THEME, Constants.EMPTY_STRING);
    }
}

