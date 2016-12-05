package hu.szakdolgozat.carsharing.data;

import android.support.v4.util.ArrayMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import hu.szakdolgozat.carsharing.BuildConfig;
import hu.szakdolgozat.carsharing.TestUtils;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.data.model.Car;
import hu.szakdolgozat.carsharing.data.model.CarPosition;
import rx.Observable;
import rx.Subscriber;

public class CarDataManager implements CarDataController {

    private static final String[] testCarNames = {"Audi", "Ford", "Toyota", "Skoda", "Smart", "BMW"};
    private ArrayMap<Long, Car> carsMap = new ArrayMap<>();

    @Override
    public Observable<List<Car>> getCarDataMap() {
        return Observable.create(new Observable.OnSubscribe<List<Car>>() {
            @Override
            public void call(final Subscriber<? super List<Car>> subscriber) {
                new FirebaseDatabaseManager().readData("cars", new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            GenericTypeIndicator<List<Car>> type = new GenericTypeIndicator<List<Car>>() {
                            };
                            List<Car> carList = dataSnapshot.getValue(type);

                            for (int i = 0, size = carList.size(); i < size; i++) {
                                Car car = carList.get(i);
                                carsMap.put(car.id, car);
                            }

                            subscriber.onNext(carList);
                            subscriber.onCompleted();
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                e.printStackTrace();
                            }
                            subscriber.onError(new JSONException(""));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(new Throwable("Database error"));
                    }
                });
            }
        });
    }

    public List<Car> generateRandomCars(int numberOfCars) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < numberOfCars; i++) {
            CarPosition carPosition = new CarPosition(
                    TestUtils.getRandomDoubleInRange(47.431311, 47.571348),
                    TestUtils.getRandomDoubleInRange(18.971101, 19.163952));
            Car car = new Car(
                    (long) i,
                    carPosition,
                    testCarNames[(int) (Math.random() * 6) % 6],
                    "ABC-123",
                    Car.FuelType.values()[(int) (Math.random() * (Car.FuelType.values().length))],
                    2500,
                    "https://www.enterprise.com/content/dam/global-vehicle-images/cars/FORD_FOCU_2012-1.png");
            cars.add(car);
        }
        return cars;
    }

    public Car getCarById(Long id) {
        return carsMap.get(id);
    }

    @Override
    public Observable<Boolean> reserveCar(Long carId) {
        return null;
    }
}
