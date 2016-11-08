package hu.szakdolgozat.carsharing.controller;

import android.widget.ImageView;

import java.io.File;

import hu.szakdolgozat.carsharing.callback.ImageLoadCallback;

public interface ImageLoaderController {

    void loadImageFromUrl(ImageView imageView, String url);
    void loadImageFromUrl(ImageView imageView, String url, final ImageLoadCallback callback);
    void loadImageFromFile(ImageView imageView, File file);

}
