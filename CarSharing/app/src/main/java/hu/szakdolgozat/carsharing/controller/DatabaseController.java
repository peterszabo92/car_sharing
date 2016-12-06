package hu.szakdolgozat.carsharing.controller;

import com.google.firebase.database.ValueEventListener;

import java.util.List;

import hu.szakdolgozat.carsharing.data.model.Car;
import rx.Observable;

public interface DatabaseController {

    void writeData(List<Car> data, String key);

    void writeData(Car data, String key);

    void readData(String key, ValueEventListener listener);

    Observable<Void> refreshCarData(Car car);
}
