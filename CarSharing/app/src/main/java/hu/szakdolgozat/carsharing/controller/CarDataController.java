package hu.szakdolgozat.carsharing.controller;

import android.support.v4.util.ArrayMap;

import hu.szakdolgozat.carsharing.data.model.Car;

public interface CarDataController {

    ArrayMap<Long, Car> getCarDataMap();

}
