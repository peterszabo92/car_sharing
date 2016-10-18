package hu.szakdolgozat.carsharing.data.interfaces;

import android.support.v4.util.ArrayMap;

import hu.szakdolgozat.carsharing.model.Car;

public interface CarDataProvider {

    ArrayMap<Long, Car> getCarDataMap();

}
