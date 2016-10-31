package hu.szakdolgozat.carsharing.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.szakdolgozat.carsharing.data.CarDataManager;
import hu.szakdolgozat.carsharing.controller.CarDataController;

@Module
public class CarDataModule {

    @Provides
    @Singleton
    CarDataController provideCarDataController() {
        return new CarDataManager();
    }

}
