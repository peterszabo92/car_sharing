package hu.szakdolgozat.carsharing.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.szakdolgozat.carsharing.controller.ImageLoaderController;
import hu.szakdolgozat.carsharing.controller.PicassoImageLoader;

@Module
public class ApplicationModule {

    private Context appContext;

    public ApplicationModule(Context applicationContext) {
        appContext = applicationContext;
    }

    @Provides
    Context provideApplicationContext() {
        return appContext;
    }

    @Provides @Singleton
    ImageLoaderController provideImageLoaderController(Context context) {
        return new PicassoImageLoader(context);
    }

}
