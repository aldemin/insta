package com.alexanr.demin.materialdesign.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

public class Database {

    private static final String DATABASE_NAME = "PhotosDB";
    private static Database database;
    private PhotosDB photosDB;

    private Database(Context context) {
        photosDB = Room.databaseBuilder(context, PhotosDB.class, DATABASE_NAME).allowMainThreadQueries().build();
    }

    public static void init(Context context) {
        if (database == null) {
            database = new Database(context);
        }
    }

    @NonNull
    public static Database get() {
        return database;
    }

    public PhotosDB getDatabase() {
        return photosDB;
    }
}
