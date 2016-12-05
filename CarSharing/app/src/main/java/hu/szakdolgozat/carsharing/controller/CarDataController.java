package hu.szakdolgozat.carsharing.controller;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.Car;
import rx.Observable;

public interface CarDataController {

    Observable<List<Car>> getCarDataMap();
    Car getCarById(Long id);

    Observable<Boolean> reserveCar(Long carId);

}
