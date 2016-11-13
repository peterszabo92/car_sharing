package hu.szakdolgozat.carsharing;


import com.squareup.picasso.Picasso;

import hu.szakdolgozat.carsharing.component.ApplicationComponent;
import hu.szakdolgozat.carsharing.component.ControllerComponent;
import hu.szakdolgozat.carsharing.component.DaggerApplicationComponent;
import hu.szakdolgozat.carsharing.component.DaggerControllerComponent;
import hu.szakdolgozat.carsharing.module.ApplicationModule;

public class CarSharingApplication extends android.app.Application {

    public static Picasso PICASSO;
    private static ApplicationComponent applicationComponent;
    private static ControllerComponent controllerComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
        controllerComponent = DaggerControllerComponent.create();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
    public static ControllerComponent getControllerComponent() {
        return controllerComponent;
    }
}