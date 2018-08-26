package com.alexanr.demin.materialdesign;

import android.graphics.Bitmap;

public class Img {
    private Bitmap img;
    private boolean isFavorite;

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
