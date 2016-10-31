package hu.szakdolgozat.carsharing;


import com.squareup.picasso.Picasso;

import hu.szakdolgozat.carsharing.component.CarDataComponent;
import hu.szakdolgozat.carsharing.component.DaggerCarDataComponent;

public class BaseApplication extends android.app.Application {

    public static Picasso PICASSO;
    private static CarDataComponent carDataComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        carDataComponent = DaggerCarDataComponent.builder().build();
        PICASSO = Picasso.with(this);
    }

    public static CarDataComponent getCarDataComponent() {
        return carDataComponent;
    }
}
