package hu.szakdolgozat.carsharing.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.controller.UserController;
import hu.szakdolgozat.carsharing.data.CarDataManager;

@Module
public class CarDataModule {

    @Provides
    @Singleton
    CarDataController provideCarDataController(UserController userController) {
        return new CarDataManager(userController);
    }

}
