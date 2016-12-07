package hu.szakdolgozat.carsharing.data;

import android.support.v4.util.ArrayMap;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.szakdolgozat.carsharing.BuildConfig;
import hu.szakdolgozat.carsharing.TestUtils;
import hu.szakdolgozat.carsharing.controller.CarDataController;
import hu.szakdolgozat.carsharing.controller.UserController;
import hu.szakdolgozat.carsharing.data.model.Car;
import hu.szakdolgozat.carsharing.data.model.CarDetail;
import hu.szakdolgozat.carsharing.data.model.CarPosition;
import hu.szakdolgozat.carsharing.data.model.FuelType;
import rx.Observable;
import rx.Subscriber;

public class CarDataManager implements CarDataController {

    private static final String[] testCarNames = {"Audi", "Ford", "Toyota", "Skoda", "Smart", "BMW"};
    private ArrayMap<Long, Car> carsMap = new ArrayMap<>();

    private UserController userController;

    private ValueEventListener valueEventListener;

    @Inject
    public CarDataManager(UserController userController) {
        this.userController = userController;
    }

    @Override
    public Observable<List<Car>> getCarDataMap() {
        return Observable.create(new Observable.OnSubscribe<List<Car>>() {
            @Override
            public void call(final Subscriber<? super List<Car>> subscriber) {
                valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            List<Car> carList = new ArrayList<Car>();

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                Car car = dataSnapshot1.getValue(Car.class);
                                carList.add(car);
                                carsMap.put(car.id, car);
                            }

                            subscriber.onNext(carList);
                            subscriber.onCompleted();
                            valueEventListener = null;
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                e.printStackTrace();
                            }
                            subscriber.onError(new JSONException(""));
                            valueEventListener = null;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        subscriber.onError(new Throwable("Database error"));
                    }
                };
                new FirebaseDatabaseManager().readData("cars", valueEventListener);
            }
        });
    }

    @Override
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
                    FuelType.values()[(int) (Math.random() * (FuelType.values().length))],
                    2500,
                    "https://www.enterprise.com/content/dam/global-vehicle-images/cars/FORD_FOCU_2012-1.png");
            cars.add(car);
        }
        return cars;
    }

    @Override
    public Observable<List<CarDetail>> getCarDetails() {
        return Observable.create(new Observable.OnSubscribe<List<CarDetail>>() {
            @Override
            public void call(final Subscriber<? super List<CarDetail>> subscriber) {
                new FirebaseDatabaseManager().readData("fleet", new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        try {
                            List<CarDetail> carDetails = new ArrayList<CarDetail>();

                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                CarDetail carDetail = dataSnapshot1.getValue(CarDetail.class);
                                carDetails.add(carDetail);
                            }

                            subscriber.onNext(carDetails);
                            subscriber.onCompleted();
                            valueEventListener = null;
                        } catch (Exception e) {
                            if (BuildConfig.DEBUG) {
                                e.printStackTrace();
                            }
                            subscriber.onError(new JSONException(""));
                            valueEventListener = null;
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

    @Override
    public boolean hasUserActiveReservation() {
        String userId = userController.getCurrentUserId();
        for (int i = 0, size = carsMap.size(); i < size; i++) {
            if (carsMap.valueAt(i).reserved != null && carsMap.valueAt(i).reserved.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public Car getCarById(Long id) {
        return carsMap.get(id);
    }

    @Override
    public Observable<Void> reserveCar(Long carId) {
        FirebaseDatabaseManager firebaseDatabaseManager = new FirebaseDatabaseManager();
        Car car = getCarById(carId);
        car.reserved = userController.getCurrentUserId();
        return firebaseDatabaseManager.refreshCarData(car);
    }

    @Override
    public Observable<Void> cancelReservation(Long carId) {
        FirebaseDatabaseManager firebaseDatabaseManager = new FirebaseDatabaseManager();
        Car car = getCarById(carId);
        car.reserved = null;
        return firebaseDatabaseManager.refreshCarData(car);
    }
}
