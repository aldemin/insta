package com.alexanr.demin.materialdesign;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImgItemsList.init(this);
    }
}
