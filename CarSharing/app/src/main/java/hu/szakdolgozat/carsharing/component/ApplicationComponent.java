package hu.szakdolgozat.carsharing.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import hu.szakdolgozat.carsharing.activity.MainActivity;
import hu.szakdolgozat.carsharing.controller.ImageLoaderController;
import hu.szakdolgozat.carsharing.module.ApplicationModule;
import hu.szakdolgozat.carsharing.module.CarDataModule;
import hu.szakdolgozat.carsharing.rent.RentPresenter;

@Singleton
@Component(modules = {ApplicationModule.class, CarDataModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    Context appContext();

    ImageLoaderController imageLoaderController();

    RentPresenter getRentPresenter();
}
