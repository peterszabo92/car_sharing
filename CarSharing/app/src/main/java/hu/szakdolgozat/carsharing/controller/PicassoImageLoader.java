package hu.szakdolgozat.carsharing.controller;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import hu.szakdolgozat.carsharing.callback.ImageLoadCallback;

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
    public void loadImageFromUrl(ImageView imageView, String url, final ImageLoadCallback callback) {
        mPicasso.load(url).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                callback.onSuccess();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void loadImageFromFile(ImageView imageView, File file) {
        mPicasso.load(file).into(imageView);
    }
}
