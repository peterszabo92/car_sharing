package hu.szakdolgozat.carsharing.component;

import javax.inject.Singleton;

import dagger.Component;
import hu.szakdolgozat.carsharing.activity.MainActivity;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.module.CarDataModule;

@Singleton
@Component(modules = {CarDataModule.class})
public interface CarDataComponent {

    void inject(MainActivity mainActivity);

    CarDataController provideCarDataController();

}
