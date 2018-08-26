package com.alexanr.demin.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class StyleSelectionActivity extends AppCompatActivity {

    RadioButton redBtn;
    RadioButton greenBtn;
    RadioButton yellowBtn;
    MenuItem saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_selection);
        initViews();
        setRadioBtnListeners();
        inflateBar();
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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_toolbar_menu, menu);
        saveBtn = menu.findItem(R.id.settings_save);
        return true;
    }

    private void setRadioBtnListeners() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton)v;
                switch (rb.getId()) {
                    case R.id.red_theme : saveBtn.setVisible(false);
                        break;
                    case R.id.green_theme : saveBtn.setVisible(true);
                        break;
                    case R.id.yellow_theme : saveBtn.setVisible(true);
                        break;
                    default:
                        break;
                }
            }
        };

        redBtn.setOnClickListener(listener);
        greenBtn.setOnClickListener(listener);
        yellowBtn.setOnClickListener(listener);
    }
}
