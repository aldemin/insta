package com.alexanr.demin.materialdesign.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Photo.class}, version = 1, exportSchema = false)
public abstract class PhotosDB extends RoomDatabase {
    public abstract PhotosDao photosDao();
}
