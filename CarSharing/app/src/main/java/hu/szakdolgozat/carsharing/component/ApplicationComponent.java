package hu.szakdolgozat.carsharing.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import hu.szakdolgozat.carsharing.controller.ImageLoaderController;
import hu.szakdolgozat.carsharing.module.ApplicationModule;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Context appContext();

    ImageLoaderController imageLoaderController();
}
