package hu.szakdolgozat.carsharing.controller;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class PicassoImageLoader implements ImageLoaderController {

    private Picasso mPicasso;

    public PicassoImageLoader(Context context) {
        mPicasso = Picasso.with(context);
    }

    @Override
    public void loadImageFromUrl(ImageView imageView, String url) {
        mPicasso.load(url).into(imageView);
    }

    @Override
    public void loadImageFromFile(ImageView imageView, File file) {
        mPicasso.load(file).into(imageView);
    }
}
