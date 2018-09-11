package com.alexanr.demin.materialdesign.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.alexanr.demin.materialdesign.Constants;
import com.alexanr.demin.materialdesign.Home.MainActivity;
import com.alexanr.demin.materialdesign.R;

public class Settings extends AppCompatActivity {
    RadioButton redBtn;
    RadioButton greenBtn;
    RadioButton yellowBtn;
    MenuItem saveBtn;
    String currentTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initTheme();
        setCurrentTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_selection);
        initViews();
        setBtnChecked();
        setRadioBtnListeners();
        inflateBar();

    }

    private void initTheme() {
        currentTheme = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(Constants.THEME, Constants.EMPTY_STRING);
    }

    private void setBtnChecked() {
        switch (currentTheme) {
            case Constants.THEME_RED_DEFAULT:
                redBtn.setChecked(true);
                break;
            case Constants.THEME_GREEN:
                greenBtn.setChecked(true);
                break;
            case Constants.THEME_YELLOW:
                yellowBtn.setChecked(true);
                break;
        }
    }

    private void initViews() {
        redBtn = findViewById(R.id.red_theme);
        greenBtn = findViewById(R.id.green_theme);
        yellowBtn = findViewById(R.id.yellow_theme);
        saveBtn = findViewById(R.id.settings_save);
        redBtn.setChecked(true);
    }

    private void inflateBar() {
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.settings_save) {
            PreferenceManager.getDefaultSharedPreferences(getApplicationContext())
                    .edit()
                    .putString(Constants.THEME, currentTheme)
                    .apply();
            setCurrentTheme();
            TaskStackBuilder.create(getApplicationContext())
                    .addNextIntent(new Intent(getApplicationContext(), MainActivity.class))
                    .addNextIntent(getIntent())
                    .startActivities();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_toolbar_menu, menu);
        saveBtn = menu.findItem(R.id.settings_save);
        return true;
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

    private void setRadioBtnListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.red_theme) {
                    if (!currentTheme.equals(Constants.THEME_RED_DEFAULT)) {
                        currentTheme = Constants.THEME_RED_DEFAULT;
                        saveBtn.setVisible(true);
                    }
                }
                if (id == R.id.green_theme) {
                    if (!currentTheme.equals(Constants.THEME_GREEN)) {
                        currentTheme = Constants.THEME_GREEN;
                        saveBtn.setVisible(true);
                    }
                }
                if (id == R.id.yellow_theme) {
                    if (!currentTheme.equals(Constants.THEME_YELLOW)) {
                        currentTheme = Constants.THEME_YELLOW;
                        saveBtn.setVisible(true);
                    }
                }
            }
        };

        redBtn.setOnClickListener(listener);
        greenBtn.setOnClickListener(listener);
        yellowBtn.setOnClickListener(listener);
    }
}
