package com.alexanr.demin.materialdesign;

import android.app.Application;

import com.alexanr.demin.materialdesign.database.Database;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Database.init(this);
    }
}
