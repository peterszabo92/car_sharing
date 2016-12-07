package hu.szakdolgozat.carsharing.controller;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.Car;
import hu.szakdolgozat.carsharing.data.model.CarDetail;
import rx.Observable;

public interface CarDataController {

    Observable<List<Car>> getCarDataMap();

    Car getCarById(Long id);

    Observable<Void> reserveCar(Long carId);

    Observable<Void> cancelReservation(Long carId);

    List<Car> generateRandomCars(int numberOfCars);

    Observable<List<CarDetail>> getCarDetails();

    boolean hasUserActiveReservation();

}
