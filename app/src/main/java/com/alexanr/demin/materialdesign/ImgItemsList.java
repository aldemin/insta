package com.alexanr.demin.materialdesign;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class ImgItemsList {

    private static ImgItemsList itemsList = null;

    private ArrayList<Img> list;

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
            for (File file : directory.listFiles()) {
                Img img = new Img();
                img.setImg(file);
                img.setName(file.getName());
                // TODO: 27.08.2018 add favorite from prefs
                img.setFavorite(false);
                list.add(img);
            }
        } else {
            // TODO: 27.08.2018 add else
        }
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Img> getList() {
        return list;
    }
}
