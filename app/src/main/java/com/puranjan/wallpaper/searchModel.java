package com.puranjan.wallpaper;

import java.util.ArrayList;

public class searchModel {
    private ArrayList<imageModel> photos;

    public searchModel(ArrayList<imageModel> photos) {
        this.photos = photos;
    }

    public ArrayList<imageModel> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<imageModel> photos) {
        this.photos = photos;
    }
}
