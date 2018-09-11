package com.alexanr.demin.materialdesign.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PhotosDao {

    @Query("SELECT * FROM photo")
    List<Photo> getAll();

    @Query("SELECT * FROM photo WHERE isFavorite = 1")
    List<Photo> getFavorites();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Photo photo);

    @Update
    void update(Photo photo);
}
