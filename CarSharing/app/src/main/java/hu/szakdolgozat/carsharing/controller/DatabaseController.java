package hu.szakdolgozat.carsharing.controller;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.Car;

public interface DatabaseController {

    void writeData(List<Car> data, String key);

    void readData(String key);

}
