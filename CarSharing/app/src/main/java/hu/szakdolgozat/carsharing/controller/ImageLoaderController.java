package hu.szakdolgozat.carsharing.controller;

import android.widget.ImageView;

import java.io.File;

public interface ImageLoaderController {

    void loadImageFromUrl(ImageView imageView, String url);
    void loadImageFromFile(ImageView imageView, File file);

}
