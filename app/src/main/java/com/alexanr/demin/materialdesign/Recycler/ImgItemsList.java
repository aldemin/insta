package com.alexanr.demin.materialdesign.Recycler;

import com.alexanr.demin.materialdesign.database.Photo;

import java.util.ArrayList;

public class ImgItemsList {

    private static ImgItemsList imgItemsList;
    private ArrayList<Photo> list;

    private ImgItemsList() {
        list = new ArrayList<>();
    }

    public void init() {
        if (imgItemsList == null) {
            imgItemsList = new ImgItemsList();
        }
    }

    public static ImgItemsList get() {
        return imgItemsList;
    }

    public ArrayList<Photo> getList() {
        return list;
    }

    public void setList(ArrayList<Photo> list) {
        this.list = list;
    }
}
