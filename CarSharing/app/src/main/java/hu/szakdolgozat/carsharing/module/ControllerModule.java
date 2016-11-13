package hu.szakdolgozat.carsharing.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.szakdolgozat.carsharing.controller.UserController;
import hu.szakdolgozat.carsharing.controller.UserManager;

@Module
public class ControllerModule {

    @Provides @Singleton
    public UserController provideUserController() {
        return new UserManager();
    }

}
