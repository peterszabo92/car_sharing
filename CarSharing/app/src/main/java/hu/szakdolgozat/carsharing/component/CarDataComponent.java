package hu.szakdolgozat.carsharing.component;

import javax.inject.Singleton;

import dagger.Component;
import hu.szakdolgozat.carsharing.activity.MainActivity;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.module.CarDataModule;
import hu.szakdolgozat.carsharing.rent.RentPresenter;

@Singleton
@Component(modules = {CarDataModule.class})
public interface CarDataComponent {

    void inject(MainActivity mainActivity);
    void inject(RentPresenter rentPresenter);

}
