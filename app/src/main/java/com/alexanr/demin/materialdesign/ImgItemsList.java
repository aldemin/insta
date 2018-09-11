package com.alexanr.demin.materialdesign;

import android.content.Context;
import android.os.Environment;

import com.alexanr.demin.materialdesign.database.Photo;

import java.io.File;
import java.util.ArrayList;

public class ImgItemsList {

    private static ImgItemsList itemsList = null;

    private ArrayList<Photo> list;

    private ImgItemsList(Context context) {
        list = new ArrayList<>();
        initFileList(context);
    }

    public static void init(Context context) {
        if (itemsList == null) {
            itemsList = new ImgItemsList(context);
        }
    }

    public static ImgItemsList get() {
        return itemsList;
    }

    private void initFileList(Context context) {
        File directory = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath());
        if (directory.listFiles().length > 0) {
/*            for (File file : directory.listFiles()) {
                Photo photo = new Photo();
                photo.set(file);
                photo.setName(file.getName());
                // TODO: 27.08.2018 add favorite from prefs
                photo.setFavorite(false);
                list.add(photo);
            }*/
        } else {
            // TODO: 27.08.2018 add else
        }
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Photo> getList() {
        return list;
    }
}
